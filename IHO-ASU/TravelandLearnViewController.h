//
//  TravelandLearnViewController.h
//  IHO-ASU
//
//  Created by Cynosure on 4/29/14.
//  Copyright (c) 2014 ASU. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface TravelandLearnViewController : UIViewController
- (IBAction)travelPlace1:(id)sender;
- (IBAction)travelPlace2:(id)sender;
@property (weak, nonatomic) IBOutlet UIScrollView *scrollContent;

@property (weak, nonatomic) IBOutlet UIView *contentView;
@end
