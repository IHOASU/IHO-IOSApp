//
//  UIViewController+ImageContent.h
//  IHO-ASU
//
//  Created by PrashMaya on 10/17/14.
//  Copyright (c) 2014 ASU. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <sqlite3.h>

@interface ImageContent: UIViewController<UIPageViewControllerDataSource>
@property (strong, nonatomic) NSString *databasePath;
@property (nonatomic) sqlite3 *asuIHO;

@property (strong, nonatomic) UIPageViewController *pageViewController;
@property (strong, nonatomic) NSMutableArray *imageCaptions;
@property (strong, nonatomic) NSMutableArray *pageImages;

@end
