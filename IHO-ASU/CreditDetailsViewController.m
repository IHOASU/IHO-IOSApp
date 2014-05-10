//
//  CreditDetailsViewController.m
//  IHO
//
//  Created by Cynosure on 4/13/14.
//  Copyright (c) 2014 asu. All rights reserved.
//

#import "CreditDetailsViewController.h"

@interface CreditDetailsViewController ()

@end

@implementation CreditDetailsViewController
@synthesize  creditText;

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
    self.navigationController.navigationBar.titleTextAttributes = @{NSForegroundColorAttributeName : [UIColor whiteColor]};

    
    //Declare the webview to display content
    NSString *path = [[NSBundle mainBundle] pathForResource:@"CreditDetails" ofType:@"html"];
    if (path){
        
        NSData *data=[NSData dataWithContentsOfFile:path];
        [creditText loadData:data MIMEType:@"text/html" textEncodingName:@"convert"  baseURL:nil];
        creditText.scrollView.scrollEnabled= FALSE;
        [self.view addSubview:creditText];
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

@end
