//
//  LecturerDetailsViewController.m
//  IHO
//
//  Created by Cynosure on 4/9/14.
//  Copyright (c) 2014 asu. All rights reserved.
//

#import "LecturerDetailsViewController.h"
#import "LecturerDetail.h"
#import <MessageUI/MessageUI.h>

@interface LecturerDetailsViewController ()
{
    LecturerDetail *detail;
}
@end

@implementation LecturerDetailsViewController

@synthesize lectID=_lectID,lectImage=_lectImage,bio=_bio,lecttitle=_lecttitle;

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
    [self.navigationController.navigationBar setBarTintColor:[UIColor colorWithRed:0.22f green:0.42f blue:0.62f alpha:1.0 ]];
    self.navigationController.navigationBar.titleTextAttributes = @{NSForegroundColorAttributeName : [UIColor whiteColor]};

    detail  = nil;
    // Do any additional setup after loading the view.
    NSString *sqLiteDb = [[NSBundle mainBundle] pathForResource:@"asuIHO" ofType:@"db"];
    
    if (sqlite3_open([sqLiteDb UTF8String],&_asuIHO)==SQLITE_OK)
    {
        
       
        detail = [self lectDetail:_lectID];
        
        // Do any additional setup after loading the view.
        if (detail!= nil) {
           
            _lectImage.image=[UIImage imageWithData:detail.image];
            _lecttitle.text = detail.title;
            _bio.text = detail.bio;
            
            
        }
        
    }
    else{
        NSLog(@"Not working");
    }

    
    
    
    
}


- (IBAction)emailQ:(id)sender {
    MFMailComposeViewController *picker = [[MFMailComposeViewController alloc] init];
    picker.mailComposeDelegate = self;
    
    
    // Set up the recipients.
    NSArray *toRecipients = [NSArray arrayWithObject:detail.email];
    [picker setToRecipients:toRecipients];
    
    
    [self presentViewController:picker animated:YES completion:nil];

    
}

- (void)mailComposeController:(MFMailComposeViewController*)controller didFinishWithResult:(MFMailComposeResult)result error:(NSError*)error{
    [self dismissViewControllerAnimated:YES completion:nil];
}

-(LecturerDetail *)lectDetail:(int)letID{
    
    sqlite3_stmt *statement;
    LecturerDetail *newItem = nil;
    NSString *query = [NSString stringWithFormat:@"SELECT LectID,Name,Image,Title,Bio,Link,Email FROM Lecturer WHERE LectID=%d",letID];
    const char *query_stmt = [query UTF8String];
    if(sqlite3_prepare_v2(_asuIHO,query_stmt,-1,&statement,NULL)==SQLITE_OK)
    {
        while(sqlite3_step(statement)==SQLITE_ROW){
            
            int LectID = sqlite3_column_int(statement, 0);
            
            //read data from the result
            NSString *Name =  [NSString  stringWithUTF8String:(char *)sqlite3_column_text(statement, 1)];
            NSData *imgData = [[NSData alloc] initWithBytes:sqlite3_column_blob(statement, 2) length:sqlite3_column_bytes(statement, 2)];
            NSString *title =  [NSString  stringWithUTF8String:(char *)sqlite3_column_text(statement, 3)];
            NSString *Bio = [NSString  stringWithUTF8String:(char *)sqlite3_column_text(statement, 4)];
            NSString *link  = [NSString  stringWithUTF8String:(char *)sqlite3_column_text(statement, 5)];
            NSString *email =  [NSString  stringWithUTF8String:(char *)sqlite3_column_text(statement, 6)];
             newItem = [[LecturerDetail alloc] initWithLectid:LectID name:Name title:title image:imgData bio:Bio link:link email:email];
            
            break;
        }
       
    }
    sqlite3_finalize(statement);
    
    sqlite3_close(_asuIHO);
    return  newItem;

    
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

- (IBAction)linkB:(id)sender {
    [[UIApplication sharedApplication] openURL:[NSURL URLWithString:detail.link]];
}




@end
