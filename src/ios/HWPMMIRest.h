/**
 *
 * In addition, this class provides all methods 
 * define here
 * @author  Anuj Singh
 * @version 1.0.0, 29/06/19
 * 
 */
#import <Cordova/CDV.h>

@interface HWPMMIRest : CDVPlugin
- (void) atlas_auto:(CDVInvokedUrlCommand*)command;
- (void) atlas_nearby:(CDVInvokedUrlCommand*)command;
- (void) atlas_geocode:(CDVInvokedUrlCommand*)command;
- (void) autos:(CDVInvokedUrlCommand*)command;
- (void) geocode:(CDVInvokedUrlCommand*)command;
- (void) revGeocode:(CDVInvokedUrlCommand*)command;
- (void) placeDetail:(CDVInvokedUrlCommand*)command;
- (void) getNearByPlaces:(CDVInvokedUrlCommand*)command;
- (void) getDistance:(CDVInvokedUrlCommand*)command;
- (void) getDistanceNew:(CDVInvokedUrlCommand*)command;
- (void) Routing_V1:(CDVInvokedUrlCommand*)command;
- (void) Routing:(CDVInvokedUrlCommand*)command;
- (void) error:(CDVInvokedUrlCommand*)command;
@end
