//
//  LecturerDetailsViewController.h
//  IHO
//
//  Created by Cynosure on 4/9/14.
//  Copyright (c) 2014 asu. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <sqlite3.h>

@class LecturerDetail;

@interface LecturerDetailsViewController : UIViewController{
    int _lectID;
}


@property (weak, nonatomic) IBOutlet UIImageView *lectImage;

@property (weak, nonatomic) IBOutlet UILabel *lecttitle;
@property (weak, nonatomic) IBOutlet UITextView *bio;
@property (weak, nonatomic) IBOutlet UIButton *linkBu;
@property (weak, nonatomic) IBOutlet UIButton *emailBu;

- (IBAction)linkB:(id)sender;

@property (nonatomic,assign) int lectID;
@property (nonatomic) sqlite3 *asuIHO;
- (IBAction)emailQ:(id)sender;

-(LecturerDetail *) lectDetail:(int)lecID;


@end
