//
//  ConnectDetailsViewController.m
//  IHO-ASU
//
//  Created by Cynosure on 4/20/14.
//  Copyright (c) 2014 ASU. All rights reserved.
//

#import "ConnectDetailsViewController.h"

@interface ConnectDetailsViewController ()

@end

@implementation ConnectDetailsViewController
@synthesize TextContact,TextLocation;

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
    

    
    self.TextLocation.selectable = YES;
    TextLocation.font = [UIFont fontWithName:@"Arial" size:9];
    [TextLocation setText:@"Social Sciences Building,Room 103\n951 South Cady Mall\nTempe, AZ 85287-4101"];
    self.TextContact.selectable = YES;
    TextContact.font = [UIFont fontWithName:@"Arial" size:9];
    NSString *contact = @"Phone:480.727.6580\nFax:480.727.6570\nEmail:iho@asu.edu";
    TextContact.text = contact;
    
      // Do any additional setup after loading the view.
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

- (IBAction)officialWebsite:(id)sender {
    NSString *link=@"https://iho.asu.edu/";
    [[UIApplication sharedApplication] openURL:[NSURL URLWithString:link]];
    
}

- (IBAction)buttonCon:(id)sender {
    NSString *link=@"https://iho.asu.edu/";
    [[UIApplication sharedApplication] openURL:[NSURL URLWithString:link]];

}

- (IBAction)buttonLoc:(id)sender {
    NSString *link=@"https://iho.asu.edu/";
    [[UIApplication sharedApplication] openURL:[NSURL URLWithString:link]];
}
@end
