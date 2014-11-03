//
//  UIViewController+ImageViewController.h
//  IHO-ASU
//
//  Created by PrashMaya on 10/17/14.
//  Copyright (c) 2014 ASU. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface ImageViewController:UIViewController
@property (weak, nonatomic) IBOutlet UIImageView *Image;
@property (weak, nonatomic) IBOutlet UILabel *IamgeCaption;



@property NSUInteger pageIndex;
//@property NSString *Text;
@property NSString *imageFile;


@end
