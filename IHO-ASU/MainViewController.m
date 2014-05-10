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
    UIImageView *imageView = [[UIImageView alloc] initWithFrame:CGRectMake(120, 34, 150,  36)];
    imageView.image = [UIImage imageNamed:@"rsz_iho_h_mg.png"];
    [self.navigationController.navigationBar setBarTintColor:[UIColor colorWithRed:0.22f green:0.42f blue:0.62f alpha:1.0 ]];
    self.navigationController.navigationBar.titleTextAttributes = @{NSForegroundColorAttributeName : [UIColor whiteColor]};

    
    [self.navigationItem setTitleView:imageView];
    news.layer.cornerRadius=15;
    [news setBackgroundColor:[UIColor colorWithRed:0.22f green:0.42f blue:0.62f alpha:1.0]];
    about.layer.cornerRadius=15;
    [about setBackgroundColor:[UIColor colorWithRed:0.22f green:0.42f blue:0.62f alpha:1.0]];
    donate.layer.cornerRadius=15;
    [donate setBackgroundColor:[UIColor colorWithRed:0.22f green:0.42f blue:0.62f alpha:1.0]];
    gallery.layer.cornerRadius=15;
    [gallery setBackgroundColor:[UIColor colorWithRed:0.22f green:0.42f blue:0.62f alpha:1.0]];
    contact.layer.cornerRadius=15;
    [contact setBackgroundColor:[UIColor colorWithRed:0.22f green:0.42f blue:0.62f alpha:1.0]];
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
    [self.navigationController.toolbar setBarTintColor:[UIColor colorWithRed:0.22f green:0.42f blue:0.62f alpha:1.0 ]];
    }




-(void) creditPage{
    
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
