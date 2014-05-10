//
//  LecturerDetail.m
//  IHO
//
//  Created by Cynosure on 4/9/14.
//  Copyright (c) 2014 asu. All rights reserved.
//

#import "LecturerDetail.h"

@implementation LecturerDetail

@synthesize bio=_bio,lectID=_lectID,link=_link,image=_image,title=_title,email=_email,name=_name;

-(id) init{
    
    return self;
}

-(id) initWithLectid:(int)lectID name:(NSString *)name title:(NSString *)title image:(NSData *)image bio:(NSString *)bio link:(NSString *)link email:(NSString *)email
{
    if ((self = [super init]))
    {
        self.lectID= lectID;
        self.name = name;
        self.image = image;
        self.title = title;
        self.bio = bio;
        self.link = link;
        self.email = email;
    }
    return self;
    
}


@end
