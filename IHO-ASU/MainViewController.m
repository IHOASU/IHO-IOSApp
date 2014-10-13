//
//  ViewController.m
//  IHO
//
//  Created by Cynosure on 11/13/13.
//  Copyright (c) 2013 asu. All rights reserved.
//

#import "MainViewController.h"

@interface MainViewController ()

@end

@implementation MainViewController

@synthesize  news,field,donate,about,gallery,contact,menu,ihoLogo,credit;

- (void)viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view, typically from a nib.
    
    bool ipad = ([[UIDevice currentDevice]userInterfaceIdiom ] == UIUserInterfaceIdiomPad);
    NSString *htmlpath = nil;
   UIImageView *imageView = [[UIImageView alloc] initWithFrame:CGRectMake(100, 34, 10,  36)];
    imageView.image = [UIImage imageNamed:@"IHOlogoforapp.jpg"];
   // self.navigationItem.titleView = [[UIImageView alloc] initWithImage:[UIImage imageNamed:@"IHOlogoforapp.jpg"]];
    //[[UINavigationBar appearance] setBarTintColor:UIColorFromRGB(0x03366)];
    //[self.navigationController.navigationBar setTintColor:UIColorFromRGB(0x003366)];
   // [self.navigationController.navigationBar setBarTintColor:[UIColor colorWithRed:0 green:0.2 blue:0.4 alpha:1]];

    self.navigationController.navigationBar.titleTextAttributes = @{NSForegroundColorAttributeName : [UIColor whiteColor]};

    
    [self.navigationItem setTitleView:imageView];
    news.layer.cornerRadius=15;
    [news setBackgroundColor:[UIColor colorWithRed:0 green:0.2 blue:0.4 alpha:1.0]];
    about.layer.cornerRadius=15;
    [about setBackgroundColor:[UIColor colorWithRed:0 green:0.2f blue:0.4f alpha:1.0]];
    donate.layer.cornerRadius=15;
    [donate setBackgroundColor:[UIColor colorWithRed:0 green:0.2f blue:0.4f alpha:1.0]];
    gallery.layer.cornerRadius=15;
    [gallery setBackgroundColor:[UIColor colorWithRed:0 green:0.2f blue:0.4f alpha:1.0]];
    contact.layer.cornerRadius=15;
    [contact setBackgroundColor:[UIColor colorWithRed:0 green:0.2f blue:0.4f alpha:1.0]];
    field.layer.cornerRadius=15;
   // [field setBackgroundColor:[UIColor colorWithRed:0.22f green:0.42f blue:0.62f alpha:1.0]];
    
    
    self.ihoLogo.scalesPageToFit = YES;
    self.ihoLogo.scrollView.scrollEnabled=NO;
    self.ihoLogo.scrollView.bounces=NO;
    if(!ipad)
    htmlpath = [[NSBundle mainBundle] pathForResource:@"skull" ofType:@"html"];
    else
     htmlpath = [[NSBundle mainBundle] pathForResource:@"skulliPad" ofType:@"html"];

    NSString *html = [NSString stringWithContentsOfFile:htmlpath encoding:NSUTF8StringEncoding error:nil];
    NSURL *baseURL = [NSURL fileURLWithPath:[NSString stringWithFormat:@"%@", [[NSBundle mainBundle] bundlePath]]];
    ihoLogo.scalesPageToFit = YES;
    [self.ihoLogo loadHTMLString:html baseURL:baseURL];
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



-(void) creditPage{
    
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
