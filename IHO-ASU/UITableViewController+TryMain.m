//
//  UITableViewController+TryMain.m
//  IHO-ASU
//
//  Created by PrashMaya on 11/10/14.
//  Copyright (c) 2014 ASU. All rights reserved.
//

#import "UITableViewController+TryMain.h"

@implementation TryMain
@synthesize connect,ihologo;

- (id)initWithStyle:(UITableViewStyle)style
{
    self = [super initWithStyle:style];
    if (self) {
        // Custom initialization
    }
    return self;
}

-(void)viewDidLoad{

    NSString *htmlpath=nil;
    NSString *html = [NSString stringWithContentsOfFile:htmlpath encoding:NSUTF8StringEncoding error:nil];
    NSURL *baseURL = [NSURL fileURLWithPath:[NSString stringWithFormat:@"%@", [[NSBundle mainBundle] bundlePath]]];
    ihologo.scalesPageToFit = NO;
    [self.ihologo loadHTMLString:html baseURL:baseURL];
    ihologo.scrollView.scrollEnabled=NO;

}

@end
