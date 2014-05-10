//
//  TravelandLearnViewController.m
//  IHO-ASU
//
//  Created by Cynosure on 4/29/14.
//  Copyright (c) 2014 ASU. All rights reserved.
//

#import "TravelandLearnViewController.h"

@interface TravelandLearnViewController ()

@end

@implementation TravelandLearnViewController

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
    [self.navigationController.navigationBar setBarTintColor:[UIColor colorWithRed:0.22f green:0.419f blue:0.619f alpha:1.0 ]];
    self.navigationController.navigationBar.titleTextAttributes = @{NSForegroundColorAttributeName : [UIColor whiteColor]};
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

- (IBAction)travelPlace1:(id)sender {
    [[UIApplication sharedApplication] openURL:[NSURL URLWithString:@"https://iho.asu.edu/outreach/travel/galapagos2014"]];

}

- (IBAction)travelPlace2:(id)sender {
    [[UIApplication sharedApplication] openURL:[NSURL URLWithString:@"https://iho.asu.edu/outreach/travel/france2013"]];

}
@end
