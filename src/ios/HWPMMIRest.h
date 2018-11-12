#import <Cordova/CDV.h>

@interface HWPMMIRest : CDVPlugin
- (void) atlas_auto:(CDVInvokedUrlCommand*)command;
- (void) atlas_nearby:(CDVInvokedUrlCommand*)command;
- (void) autos:(CDVInvokedUrlCommand*)command;
- (void) geocode:(CDVInvokedUrlCommand*)command;
- (void) revGeocode:(CDVInvokedUrlCommand*)command;
- (void) placeDetail:(CDVInvokedUrlCommand*)command;
- (void) getNearByPlaces:(CDVInvokedUrlCommand*)command;
- (void) getDistance:(CDVInvokedUrlCommand*)command;
- (void) Routing:(CDVInvokedUrlCommand*)command;
- (void) get:(CDVInvokedUrlCommand*)command;
- (void) error:(CDVInvokedUrlCommand*)command;
@end
