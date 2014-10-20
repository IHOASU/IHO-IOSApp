//
//  UIViewController+ImageContent.m
//  IHO-ASU
//
//  Created by PrashMaya on 10/17/14.
//  Copyright (c) 2014 ASU. All rights reserved.
//

#import "UIViewController+ImageContent.h"

@implementation ImageContent


- (void)viewDidLoad
{
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    _pageImages = [[NSMutableArray alloc]init];
    NSString *sqLiteDb = [[NSBundle mainBundle] pathForResource:@"asuIHO" ofType:@"db"];
    
    sqlite3_stmt *statement;
    
    if (sqlite3_open([sqLiteDb UTF8String],&_asuIHO)==SQLITE_OK)
    {
        NSString *query = [NSString stringWithFormat:@"SELECT imageID,imageName,imageCaption FROM Gallery where LectID is NULL"];
        const char *query_stmt = [query UTF8String];
        if(sqlite3_prepare_v2(_asuIHO,query_stmt,-1,&statement,NULL)==SQLITE_OK)
        {
            while(sqlite3_step(statement)==SQLITE_ROW)
            {
                if (sqlite3_column_blob(statement, 1)){
                    //read data from the result
                    NSData *imgData = [[NSData alloc] initWithBytes:sqlite3_column_blob(statement, 1) length:sqlite3_column_bytes(statement, 1)];
                    NSString *caption  = [NSString  stringWithUTF8String:(char *)sqlite3_column_text(statement, 2)];
                    
                    if(imgData==nil)
                        NSLog(@"No data present");
                    else
                        //UIImage *img = [[UIImage alloc]initWithData:imgData ];
                        [_pageImages addObject:imgData];
                    [_imageCaptions addObject:caption];
                    
                    
                    NSLog(@"retrieved images");
                }
                else
                    NSLog(@"No bytes in data");
                
            }
        }
        sqlite3_finalize(statement);
    }
    sqlite3_close(_asuIHO);
    
    //return passImages;
    
    
    
    
    
    //images = [NSArray arrayWithObjects:@"fossils.jpg",@"hadar_landscape.jpg", nil];
}


@end
