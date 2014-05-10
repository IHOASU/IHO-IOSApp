//
//  EventDetailsViewController.m
//  IHO-ASU
//
//  Created by Cynosure on 4/23/14.
//  Copyright (c) 2014 ASU. All rights reserved.
//

#import "EventDetailsViewController.h"
#import "EventsDetail.h"

@interface EventDetailsViewController ()
{
    EventsDetail *event;
}
@end

@implementation EventDetailsViewController

@synthesize whenDetail,whereDetail,descDetail,eventID=_eventID;

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    [self.navigationController.navigationBar setBarTintColor:[UIColor colorWithRed:0.22f green:0.42f blue:0.62f alpha:1.0 ]];
    self.navigationController.navigationBar.titleTextAttributes = @{NSForegroundColorAttributeName : [UIColor whiteColor]};

    event  = nil;
    // Do any additional setup after loading the view.
    NSString *sqLiteDb = [[NSBundle mainBundle] pathForResource:@"asuIHO" ofType:@"db"];
    
    if (sqlite3_open([sqLiteDb UTF8String],&_asuIHO)==SQLITE_OK)
    {
        
        
        event = [self eventDetail:_eventID];
        
        // Do any additional setup after loading the view.
        if (event!= nil) {
            [whenDetail setText:event.when];
            [whereDetail setText:event.where];
            [descDetail setText:event.description];
        }
        
    }
    else{
        NSLog(@"Not working");
    }
    
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

-(EventsDetail *)eventDetail:(int)eventId{
    EventsDetail *item = nil;
    sqlite3_stmt *statement;
    
    NSString *query = [NSString stringWithFormat:@"SELECT EventId,EventWhen,EventWhere,mapLink,EventDesc,EventReg FROM Events where EventId=%d",eventId];
    const char *query_stmt = [query UTF8String];
    if(sqlite3_prepare_v2(_asuIHO,query_stmt,-1,&statement,NULL)==SQLITE_OK)
    {
        while(sqlite3_step(statement)==SQLITE_ROW){
            
            int iD = sqlite3_column_int(statement, 0);
            
            //read data from the result
            NSString *when =  [NSString  stringWithUTF8String:(char *)sqlite3_column_text(statement, 1)];
            NSString *where = [NSString  stringWithUTF8String:(char *)sqlite3_column_text(statement, 2)];
            NSString *mapLink  = [NSString  stringWithUTF8String:(char *)sqlite3_column_text(statement, 3)];
            NSString *desc  = [NSString  stringWithUTF8String:(char *)sqlite3_column_text(statement, 4)];
            NSString *registerLink  = [NSString  stringWithUTF8String:(char *)sqlite3_column_text(statement, 5)];


            item = [[EventsDetail alloc] initWitheventid:iD when:when where:where mapLink:mapLink description:desc registerLink:registerLink];
            
            break;
        }
    }
    sqlite3_finalize(statement);
    
    sqlite3_close(_asuIHO);
    return item;
}

- (IBAction)mapIt:(id)sender {
    [[UIApplication sharedApplication] openURL:[NSURL URLWithString:event.maplink]];
}

- (IBAction)registerEvent:(id)sender {
    [[UIApplication sharedApplication] openURL:[NSURL URLWithString:event.registerLink]];
}


@end
