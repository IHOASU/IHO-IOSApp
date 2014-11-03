//
//  UITableViewController+ScienceList.m
//  IHO-ASU
//
//  Created by PrashMaya on 10/25/14.
//  Copyright (c) 2014 ASU. All rights reserved.
//

#import "UITableViewController+ScienceList.h"
#import "Science.h"


@interface ScienceList()
{
    NSArray *scItems;
}
@end

@implementation ScienceList

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
    // Do any additional setup after loading the view.
    
    // [self.navigationController.navigationBar setBarTintColor:[UIColor colorWithRed:0.22f green:0.42f blue:0.62f alpha:1.0 ]];
    // self.navigationController.navigationBar.titleTextAttributes = @{NSForegroundColorAttributeName : [UIColor whiteColor]};
    
    scItems = [[NSArray alloc] init];
    NSString *sqLiteDb = [[NSBundle mainBundle] pathForResource:@"asuIHO" ofType:@"db"];
    
    if (sqlite3_open([sqLiteDb UTF8String],&_asuIHO)==SQLITE_OK)
    {
        scItems = [self scDetailsInfo];
    }
}

-(NSArray *) scDetailsInfo{
    NSMutableArray *obj = [[NSMutableArray alloc ] init ];
    sqlite3_stmt *statement;
    
    NSString *query = [NSString stringWithFormat:@"SELECT ScienceID,ScienceTitle,ScienceLink FROM Science"];
    const char *query_stmt = [query UTF8String];
    if(sqlite3_prepare_v2(_asuIHO,query_stmt,-1,&statement,NULL)==SQLITE_OK)
    {
        while(sqlite3_step(statement)==SQLITE_ROW){
            
            int SCID = sqlite3_column_int(statement, 0);
            
            //read data from the result
            
            NSString *title =  [NSString  stringWithUTF8String:(char *)sqlite3_column_text(statement, 1)];
            NSString *link  = [NSString  stringWithUTF8String:(char *)sqlite3_column_text(statement, 2)];
            Science *newItem = [[Science alloc] initWithScid:SCID Sciencetitle:title ScienceLink:link];
            if(title==nil)
                NSLog(@"No data present");
            else
                //UIImage *img = [[UIImage alloc]initWithData:imgData ];
                [obj addObject:newItem];
            
            
            NSLog(@"retrieved data");
            
        }
    }
    sqlite3_finalize(statement);
    
    sqlite3_close(_asuIHO);
    return obj;
}



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
    
    return [scItems count];
    
}


- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    static NSString *CellIdentifier = @"scCell";
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:CellIdentifier forIndexPath:indexPath];
    Science *Item = [scItems objectAtIndex:indexPath.row];
    // [cell setBackgroundColor:[UIColor colorWithRed:0.22f green:0.42f blue:0.62f alpha:1.0 ]];
    [cell.textLabel setTextColor:[UIColor colorWithWhite:1.0 alpha:1.0]];
    [cell.textLabel setFont:[UIFont fontWithName:@"Arial-BoldMT" size:15]];
    [cell.textLabel setText:[NSString stringWithString:Item.Sciencetitle]];
    return cell;
    
}


-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
        [self.tableView deselectRowAtIndexPath:self.tableView.indexPathForSelectedRow animated:YES];
        
        Science *info  = [scItems objectAtIndex:indexPath.row];
        [[UIApplication sharedApplication] openURL:[NSURL URLWithString:info.ScienceLink]];
    
    
    
}


-(void)reloadTableView
{
    
    [self reloadTableView];
    
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


@end
