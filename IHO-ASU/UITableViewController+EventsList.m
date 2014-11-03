//
//  UITableViewController+EventsList.m
//  IHO-ASU
//
//  Created by PrashMaya on 10/27/14.
//  Copyright (c) 2014 ASU. All rights reserved.
//

#import "UITableViewController+EventsList.h"
#import "EventsDetail.h"
#import "EventDetailsViewController.h"

@interface EventsList ()
{
   
    NSArray *eventItems;
}
@end

@implementation EventsList 

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
     eventItems = [[NSArray alloc] init];
    NSString *sqLiteDb = [[NSBundle mainBundle] pathForResource:@"asuIHO" ofType:@"db"];
    
    if (sqlite3_open([sqLiteDb UTF8String],&_asuIHO)==SQLITE_OK)
    {
     
        eventItems = [self eventsDetailInfo];
    }
    
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
    return 1;
}



- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
#warning Incomplete method implementation.
    // Return the number of rows in the section.
    
        return [eventItems count];
    
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
   
        static NSString *CellIdentifier = @"eventsCell";
        UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:CellIdentifier forIndexPath:indexPath];
        
        //imageView.image = [images objectAtIndex:indexPath.row];
        
        EventsDetail *Item = [eventItems objectAtIndex:indexPath.row];
        // [cell setBackgroundColor:[UIColor colorWithRed:0.22f green:0.42f blue:0.62f alpha:1.0 ]];
        [cell.textLabel setFont:[UIFont fontWithName:@"Arial-BoldMT" size:15]];
        [cell.textLabel setTextColor:[UIColor colorWithWhite:1.0 alpha:1.0]];
        [cell.textLabel setText:[NSString stringWithString:Item.title]];
        return cell;
   }



- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender{
    
        NSIndexPath *indexPath = [self.tableView indexPathForSelectedRow];
        
        EventsDetail *info = [eventItems objectAtIndex:indexPath.row];
        EventDetailsViewController *getDetails = segue.destinationViewController;
        getDetails.eventID= info.eventId;
       
    
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
