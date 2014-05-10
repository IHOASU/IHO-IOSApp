//
//  DonateSiteViewController.m
//  IHO
//
//  Created by Cynosure on 12/10/13.
//  Copyright (c) 2013 asu. All rights reserved.
//

#import "DonateSiteViewController.h"

@interface DonateSiteViewController ()

@end

@implementation DonateSiteViewController

@synthesize asuLink;

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
    
    //menubar specifications
    [self.navigationController.navigationBar setBarTintColor:[UIColor colorWithRed:0.22f green:0.42f blue:0.62f alpha:1.0 ]];
    self.navigationController.navigationBar.titleTextAttributes = @{NSForegroundColorAttributeName : [UIColor whiteColor]};

    
    //load the site into webview
    NSString *link=@"https://securelb.imodules.com/s/1469/foundation/Inner2Columns3.aspx?sid=1469&gid=2&pgid=426&cid=1155&bledit=1&dids=216";
    NSURL *url = [NSURL URLWithString:link];
    NSURLRequest *link_request = [NSURLRequest requestWithURL:url];
    [asuLink loadRequest:link_request];
    [self.view addSubview:asuLink];
    
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
