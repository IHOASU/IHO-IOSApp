//
//  UITableViewController+TryMain.m
//  IHO-ASU
//
//  Created by PrashMaya on 11/10/14.
//  Copyright (c) 2014 ASU. All rights reserved.
//

#import "UITableViewController+TryMain.h"
static sqlite3 *asuIHO = nil;

@implementation TryMain
@synthesize  news,field,donate,about,gallery,connect,ihologo,customItem1,coR,credits;

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
     [self copyDatabase];
    
    [super viewDidLoad];
    // Do any additional setup after loading the view, typically from a nib.
    UIImageView *imageView;
    bool ipad = ([[UIDevice currentDevice]userInterfaceIdiom ] == UIUserInterfaceIdiomPad);
    NSString *htmlpath = nil;
    if(!ipad){
        imageView = [[UIImageView alloc] initWithFrame:CGRectMake(0,0,self.navigationController.navigationBar.frame.size.width/1.5, self.navigationController.navigationBar.frame.size.height/1.5)];
    }
    else
    {
        imageView = [[UIImageView alloc] initWithFrame:CGRectMake(0,0,self.navigationController.navigationBar.frame.size.width/3, self.navigationController.navigationBar.frame.size.height/1.5)];
        
    }
    NSLog(@"%f",self.navigationController.navigationController.navigationBar.frame.size.height);
    imageView.image = [UIImage imageNamed:@"IHOlogoforapp.jpg"];
    UIView *logoView = [[UIView alloc] initWithFrame:CGRectMake(0, 0, imageView.frame.size.width, imageView.frame.size.height)];
    [logoView addSubview:imageView];
    
    
    [self.navigationItem setTitleView:logoView];
    
    news.layer.cornerRadius=15;
    //[news setBackgroundColor:[UIColor colorWithRed:0 green:0.2 blue:0.4 alpha:1.0]];
    about.layer.cornerRadius=15;
    //[about setBackgroundColor:[UIColor colorWithRed:0 green:0.2f blue:0.4f alpha:1.0]];
    donate.layer.cornerRadius=15;
    // [donate setBackgroundColor:[UIColor colorWithRed:0 green:0.2f blue:0.4f alpha:1.0]];
    gallery.layer.cornerRadius=15;
    //[gallery setBackgroundColor:[UIColor colorWithRed:0 green:0.2f blue:0.4f alpha:1.0]];
    connect.layer.cornerRadius=15;
    //[contact setBackgroundColor:[UIColor colorWithRed:0 green:0.2f blue:0.4f alpha:1.0]];
    field.layer.cornerRadius=15;
    // [field setBackgroundColor:[UIColor colorWithRed:0.22f green:0.42f blue:0.62f alpha:1.0]];
    
    
    
    if(!ipad)
        htmlpath = [[NSBundle mainBundle] pathForResource:@"skull" ofType:@"html"];
    else{
        htmlpath = [[NSBundle mainBundle] pathForResource:@"skulliPad" ofType:@"html"];
        [ihologo setFrame:CGRectMake(102,383 , 593, 475)];
    }
    
    NSString *html = [NSString stringWithContentsOfFile:htmlpath encoding:NSUTF8StringEncoding error:nil];
    NSURL *baseURL = [NSURL fileURLWithPath:[NSString stringWithFormat:@"%@", [[NSBundle mainBundle] bundlePath]]];
    ihologo.scalesPageToFit = NO;
    [self.ihologo loadHTMLString:html baseURL:baseURL];
    ihologo.scrollView.scrollEnabled=NO;
    
    //set up toolbar
    self.navigationController.toolbarHidden = NO;
    [self.navigationController.toolbar setTranslucent:NO];
    
    NSShadow *shadow = [NSShadow new];
    [shadow setShadowColor:[UIColor colorWithRed:1.0 green:1.0 blue:1.0 alpha:1.0]];
    [shadow setShadowOffset:CGSizeMake(0, 1)];
    
    NSDictionary *textAttributes = [NSDictionary dictionaryWithObjectsAndKeys:[UIFont fontWithName:@"Arial-MT" size:14],
                                    NSFontAttributeName,[UIColor whiteColor],NSForegroundColorAttributeName,nil] ;
    
    [coR setTitleTextAttributes:textAttributes forState:UIControlStateNormal];
    [coR setTintColor:[UIColor colorWithWhite:1 alpha:1]];
    [credits setTitleTextAttributes:textAttributes forState:UIControlStateNormal];
    [credits setTintColor:[UIColor colorWithWhite:1 alpha:1]];
    
}


//code from apple developers website

 -(void)copyDatabase
{
    /* NSString* documentsPath = [NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES) objectAtIndex:0];
     NSString* dbPath = [documentsPath stringByAppendingPathComponent:@"StayhealthyExercises-1.sqlite"];
     BOOL fileExists = [[NSFileManager defaultManager] fileExistsAtPath:dbPath];
     
     if (!fileExists) {
     NSLog(@"Didn't find database");
     // get the source path to copy from
     // NSString *dbSourcePath = [[NSBundle mainBundle] pathForResource:@"StayhealthyExercises-1" ofType:@"sqlite"];
     NSString *dbSourcePath = [[[NSBundle mainBundle] resourcePath  ]stringByAppendingPathComponent:@"StayhealthyExercises-1.sqlite"];
     // copy db to documents
     [[NSFileManager defaultManager] copyItemAtPath:dbSourcePath toPath:dbPath error:nil];
     }*/
    //create path to Application Support directory
    NSString *databasePath = [NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES) objectAtIndex:0];
   // NSArray* paths= [[NSFileManager defaultManager] URLsForDirectory:NSApplicationSupportDirector                                                              inDomains:NSUserDomainMask];
    NSString *dbPath = [databasePath stringByAppendingPathComponent:@"asuIHO.db"];
    BOOL fileExisting = [[NSFileManager defaultManager ]fileExistsAtPath:dbPath];
    
    if(!fileExisting)
   //if([paths count] > 0)
   {
       //retrieve path of database file and construct URL to it
       //NSString *pathBundle = [[NSBundle mainBundle] pathForResource:@"asuIHO" ofType:@"db"];
       NSURL *urlBundle = [[NSBundle mainBundle] URLForResource:@"asuIHO" withExtension:@"db"];
      // NSURL *urlBundle = [NSURL fileURLWithPath:pathBundle];
       
       
       
       //create application support
       NSString *appSupportDir = [NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES) lastObject];
       //In case of no Application Support Directory
       if (![[NSFileManager defaultManager] fileExistsAtPath:appSupportDir isDirectory:NULL]) {
           NSError *error = nil;
           //create it
           if (![[NSFileManager defaultManager] createDirectoryAtPath:appSupportDir withIntermediateDirectories:YES attributes:nil error:&error]) {
               NSLog(@"%@", error.localizedDescription);
           }
       }
       
       NSURL *copyURL = [NSURL fileURLWithPath:appSupportDir];
       
       /*
       //dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0), ^{
          NSError *error = nil;
           // Just try to copy the directory.
       if(![[NSFileManager defaultManager] copyItemAtURL:urlBundle toURL:copyURL error:&error]){
           NSLog(@"%@",error.localizedDescription);
           ;}
       */
       NSString *nameDB = @"asuIHO.db";
       NSData *dataDB = [NSData dataWithContentsOfURL:urlBundle];
       NSString *path = [[NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES) lastObject] stringByAppendingPathComponent:nameDB];
       [[NSFileManager defaultManager] createFileAtPath:path contents:dataDB attributes:nil];
       
       //});
   }
    
}


/*- (void)awakeFromNib
 {
 isShowingLandscapeView = NO;
 [[UIDevice currentDevice] beginGeneratingDeviceOrientationNotifications];
 [[NSNotificationCenter defaultCenter] addObserver:self
 selector:@selector(orientationChanged:)
 name:UIDeviceOrientationDidChangeNotification
 object:nil];
 }
 
 - (void)orientationChanged:(NSNotification *)notification
 {
 UIDeviceOrientation deviceOrientation = [UIDevice currentDevice].orientation;
 if (UIDeviceOrientationIsLandscape(deviceOrientation) &&
 !isShowingLandscapeView)
 {
 [self performSegueWithIdentifier:@"DisplayAlternateView" sender:self];
 isShowingLandscapeView = YES;
 }
 else if (UIDeviceOrientationIsPortrait(deviceOrientation) &&
 isShowingLandscapeView)
 {
 [self dismissViewControllerAnimated:YES completion:nil];
 isShowingLandscapeView = NO;
 }
 }*/



-(void) viewWillAppear:(BOOL)animated{
    
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}





@end
