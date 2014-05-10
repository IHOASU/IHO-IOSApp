//
//  GalleryViewController.m
//  IHO
//
//  Created by Cynosure on 12/11/13.
//  Copyright (c) 2013 asu. All rights reserved.
//

#import "GalleryViewController.h"
#import <sqlite3.h>
#import "GalleryShowImageViewController.h"

@interface GalleryViewController ()

{
    NSMutableArray *images;
   }

@end

@implementation GalleryViewController


static GalleryViewController *_database;

+ (GalleryViewController*)database {
    if (_database == nil) {
        _database = [[GalleryViewController alloc] init];
    }
    return _database;
}

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
    images = [[NSMutableArray alloc]init];
     NSString *sqLiteDb = [[NSBundle mainBundle] pathForResource:@"asuIHO" ofType:@"db"];

    sqlite3_stmt *statement;
        
        if (sqlite3_open([sqLiteDb UTF8String],&_asuIHO)==SQLITE_OK)
        {
            NSString *query = [NSString stringWithFormat:@"SELECT imageID,imageName FROM Gallery"];
            const char *query_stmt = [query UTF8String];
            if(sqlite3_prepare_v2(_asuIHO,query_stmt,-1,&statement,NULL)==SQLITE_OK)
            {
                while(sqlite3_step(statement)==SQLITE_ROW)
                {
                if (sqlite3_column_blob(statement, 1)){
                    //read data from the result
                    NSData *imgData = [[NSData alloc] initWithBytes:sqlite3_column_blob(statement, 1) length:sqlite3_column_bytes(statement, 1)];
                    
                        if(imgData==nil)
                            NSLog(@"No data present");
                            else
                    //UIImage *img = [[UIImage alloc]initWithData:imgData ];
                        [images addObject:imgData];
                        
                    
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



-(NSInteger)collectionView:(UICollectionView *)collectionView numberOfItemsInSection:(NSInteger)section
{
    return images.count;
}

-(UICollectionViewCell *)collectionView:(UICollectionView *)collectionView cellForItemAtIndexPath:(NSIndexPath *)indexPath

{
    UICollectionViewCell *cell = [collectionView dequeueReusableCellWithReuseIdentifier:@"Cell" forIndexPath:indexPath];
    
    //UIImageView *imageView = (UIImageView *)[cell viewWithTag:100];
    UIImageView *imageView = [[UIImageView alloc] initWithFrame:CGRectMake(80,70,213,170)];
    //imageView.image = [images objectAtIndex:indexPath.row];
    imageView.image = [UIImage imageWithData:[images objectAtIndex:indexPath.row]];
    
    return cell;
}



- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    if ([segue.identifier isEqualToString:@"imageSegue"]) {
        NSArray *indexPaths = [self.collectionView indexPathsForSelectedItems];
        GalleryShowImageViewController *destViewController = segue.destinationViewController;
        NSIndexPath *indexPath = [indexPaths objectAtIndex:0];
        destViewController.eachImage = [images[indexPath.section] objectAtIndex:indexPath.row];
        [self.collectionView deselectItemAtIndexPath:indexPath animated:NO];
    }
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
