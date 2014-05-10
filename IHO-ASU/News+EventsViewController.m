//
//  News+EventsViewController.m
//  IHO
//
//  Created by Cynosure on 11/17/13.
//  Copyright (c) 2013 asu. All rights reserved.
//

#import "News+EventsViewController.h"
#import "NewsDetailViewController.h"
#import "NewsDetail.h"
#import "EventsDetail.h"
#import "EventDetailsViewController.h"

@interface News_EventsViewController ()
{
    NSArray *newsItems;
    NSArray *eventItems;
}
@end

@implementation News_EventsViewController

- (id)initWithStyle:(UITableViewStyle)style
{
    self = [super initWithStyle:style];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    
    

    // Uncomment the following line to preserve selection between presentations.
    // self.clearsSelectionOnViewWillAppear = NO;
 
    //menubar specifications
    [self.navigationController.navigationBar setBarTintColor:[UIColor colorWithRed:0.22f green:0.42f blue:0.62f alpha:1.0 ]];
    self.navigationController.navigationBar.titleTextAttributes = @{NSForegroundColorAttributeName : [UIColor whiteColor]};

    [[UINavigationBar appearance] setTintColor:[UIColor whiteColor]];

    newsItems = [[NSArray alloc] init];
    eventItems = [[NSArray alloc] init];
    NSString *sqLiteDb = [[NSBundle mainBundle] pathForResource:@"asuIHO" ofType:@"db"];
    
    if (sqlite3_open([sqLiteDb UTF8String],&_asuIHO)==SQLITE_OK)
    {
        newsItems = [self newsDetailInfo];
        eventItems = [self eventsDetailInfo];
    }
    
}


-(NSArray *) newsDetailInfo{
    
    NSMutableArray *obj = [[NSMutableArray alloc ] init ];
    sqlite3_stmt *statement;
    
    NSString *query = [NSString stringWithFormat:@"SELECT NewsId,NewsTitle,NewsImage,NewsText,NewsLink FROM News"];
    const char *query_stmt = [query UTF8String];
    if(sqlite3_prepare_v2(_asuIHO,query_stmt,-1,&statement,NULL)==SQLITE_OK)
    {
        while(sqlite3_step(statement)==SQLITE_ROW){
            
            int iD = sqlite3_column_int(statement, 0);
            //read data from the result
            NSString *title =  [NSString  stringWithUTF8String:(char *)sqlite3_column_text(statement, 1)];
            NSData *imgData = [[NSData alloc] initWithBytes:sqlite3_column_blob(statement, 2) length:sqlite3_column_bytes(statement, 2)];
            NSString *text = [NSString  stringWithUTF8String:(char *)sqlite3_column_text(statement, 3)];
            NSString *link  = [NSString  stringWithUTF8String:(char *)sqlite3_column_text(statement, 4)];
            NewsDetail *newItem = [[NewsDetail alloc] initWithnewsid:iD newstitle:title image:imgData text:text newslink:link];
            if(title==nil)
                NSLog(@"No data present");
            else
                //UIImage *img = [[UIImage alloc]initWithData:imgData ];
                [obj addObject:newItem];
            
            
            NSLog(@"retrieved data");
            
        }
    }
    sqlite3_finalize(statement);
    return obj;
    
}

-(NSArray *) eventsDetailInfo{
    NSMutableArray *obj = [[NSMutableArray alloc ] init ];
    sqlite3_stmt *statement;
    
    NSString *query = [NSString stringWithFormat:@"SELECT EventId,EventTitle FROM Events"];
    const char *query_stmt = [query UTF8String];
    if(sqlite3_prepare_v2(_asuIHO,query_stmt,-1,&statement,NULL)==SQLITE_OK)
    {
        while(sqlite3_step(statement)==SQLITE_ROW){
            
            int iD = sqlite3_column_int(statement, 0);
            //read data from the result
            NSString *title =  [NSString  stringWithUTF8String:(char *)sqlite3_column_text(statement, 1)];
            EventsDetail *eventItem = [[EventsDetail alloc] initWithTitle:iD title:title];
            if(title==nil)
                NSLog(@"No data present");
            else
                //UIImage *img = [[UIImage alloc]initWithData:imgData ];
                [obj addObject:eventItem];
            
            
            NSLog(@"retrieved data");
            
        }
    }
    sqlite3_finalize(statement);
    
    sqlite3_close(_asuIHO);
    return obj;

    
}



- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

#pragma mark - Table view data source

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
#warning Potentially incomplete method implementation.
    // Return the number of sections.
    return 3;
}

-(NSString *)tableView:(UITableView *)tableView titleForHeaderInSection:(NSInteger)section {
    
    
    if(section==0){
        [UIFont fontWithName:@"Arial-BoldMT" size:10];
        return @"News";}
    else if(section==1){
        return @"Event";}
    else
        return @"Travel and Learn";
    
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
#warning Incomplete method implementation.
    // Return the number of rows in the section.
    if(section==0)
    return [newsItems count];
    else  if(section==1)
    return [eventItems count];
    return 1;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    if(indexPath.section==0){
    static NSString *CellIdentifier = @"newsCell";
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:CellIdentifier forIndexPath:indexPath];
    
      //imageView.image = [images objectAtIndex:indexPath.row];
   
    NewsDetail *Item = [newsItems objectAtIndex:indexPath.row];
    [cell setBackgroundColor:[UIColor colorWithRed:0.22f green:0.42f blue:0.62f alpha:1.0 ]];
    [cell.textLabel setFont:[UIFont fontWithName:@"Arial-BoldMT" size:15]];
    [cell.textLabel setTextColor:[UIColor colorWithWhite:1.0 alpha:1.0]];
    [cell.textLabel setText:[NSString stringWithString:Item.newstitle]];
        return cell;
    }
    
    // Configure the cell...
    if(indexPath.section==1){
        static NSString *CellIdentifier = @"eventCell";
        UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:CellIdentifier forIndexPath:indexPath];
        
        //imageView.image = [images objectAtIndex:indexPath.row];
        
        EventsDetail *Item = [eventItems objectAtIndex:indexPath.row];
        [cell setBackgroundColor:[UIColor colorWithRed:0.22f green:0.42f blue:0.62f alpha:1.0 ]];
        [cell.textLabel setFont:[UIFont fontWithName:@"Arial-BoldMT" size:15]];
        [cell.textLabel setTextColor:[UIColor colorWithWhite:1.0 alpha:1.0]];
        [cell.textLabel setText:[NSString stringWithString:Item.title]];
        return cell;
    }
    
    if(indexPath.section==2){
        static NSString *CellIdentifier = @"travelCell";
        UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:CellIdentifier forIndexPath:indexPath];
        [cell.textLabel setFont:[UIFont fontWithName:@"Arial-BoldMT" size:15]];
        [cell setBackgroundColor:[UIColor colorWithRed:0.22f green:0.42f blue:0.62f alpha:1.0 ]];
        [cell.textLabel setTextColor:[UIColor colorWithWhite:1.0 alpha:1.0]];
        [cell.textLabel setText:@"IHO TRAVEL AND LEARN"];
    
        return cell;
    }
    
    
    return nil;
}


- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender{
      if( [[segue identifier] isEqualToString:@"news"]){
    NSIndexPath *indexPath = [self.tableView indexPathForSelectedRow];
   
    NewsDetail *info = [newsItems objectAtIndex:indexPath.row];
    NewsDetailViewController *getDetails = segue.destinationViewController;
    getDetails.newsId = info.newsId;
      }
    
    if( [[segue identifier] isEqualToString:@"events"]) {
    NSIndexPath *indexPath = [self.tableView indexPathForSelectedRow];
        
    EventsDetail *info = [eventItems objectAtIndex:indexPath.row];
    EventDetailsViewController *getDetails = segue.destinationViewController;
    getDetails.eventID= info.eventId;
    }
    
    if( [[segue identifier] isEqualToString:@"travelSegue"]) {
        NSIndexPath *indexPath = [self.tableView indexPathForSelectedRow];
        
    }
    
    
}



-(void)reloadTableView
{
    
    [self reloadTableView];
    
}



- (void)viewWillAppear:(BOOL)animated
{
    [super viewWillAppear:animated];
    
    [self.tableView deselectRowAtIndexPath:self.tableView.indexPathForSelectedRow animated:YES];
}

@end
