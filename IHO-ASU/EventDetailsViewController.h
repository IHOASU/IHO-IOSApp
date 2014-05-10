//
//  EventDetailsViewController.h
//  IHO-ASU
//
//  Created by Cynosure on 4/23/14.
//  Copyright (c) 2014 ASU. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <sqlite3.h>

@class EventsDetail;

@interface EventDetailsViewController : UIViewController{
    int _eventID;
}
@property (weak, nonatomic) IBOutlet UITextView *whenDetail;

@property (weak, nonatomic) IBOutlet UITextView *whereDetail;

@property (weak, nonatomic) IBOutlet UITextView *descDetail;

- (IBAction)mapIt:(id)sender;


- (IBAction)registerEvent:(id)sender;
@property (nonatomic) sqlite3 *asuIHO;

-(EventsDetail *)eventDetail:(int)eventId;

@property (nonatomic,assign) int eventID;



@end
