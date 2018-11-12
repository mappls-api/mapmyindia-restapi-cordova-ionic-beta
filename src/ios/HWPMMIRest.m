#import "HWPMMIRest.h"

@implementation HWPMMIRest

NSString *BASE_REQ_URL = @"https://apis.mapmyindia.com/advancedmaps/v1";
NSString *OUTPOST_URL = @"https://outpost.mapmyindia.com/api/security/oauth/token";
NSString *ATLAS_URL = @"https://atlas.mapmyindia.com/api/places";
NSString *oauth2_token_type = @"bearer";
NSDictionary *jsonResponse;
int current_time;
int access_token_validity;

CDVPluginResult* pluginResult = nil;

- (void)get:(CDVInvokedUrlCommand*)command
    NSString* final_url = [[command arguments] objectAtIndex:0];
    [self getHttp: final_url :command];
}

- (void)error:(CDVInvokedUrlCommand*)command
{
  jsonResponse = [NSDictionary dictionaryWithObjectsAndKeys:
                      @"400", @"status",
                      @"parameters are missing", @"message", nil];
  pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsDictionary: jsonResponse];
  [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (void)autos:(CDVInvokedUrlCommand*)command
{
    NSString* key = [[command arguments] objectAtIndex:0];
    NSString* query = [[command arguments] objectAtIndex:1];
    NSString *final_url = [NSString stringWithFormat:@"%@/%@/autosuggest?q=%@", BASE_REQ_URL,key, query];
    [self getHttp: final_url :command];
}

- (void)atlas_auto:(CDVInvokedUrlCommand*)command
{
    [self setTime];
    NSString* client_id = [[command arguments] objectAtIndex:0];
    NSString* client_secret = [[command arguments] objectAtIndex:1];
    NSString* query = [[command arguments] objectAtIndex:2];
    NSString *final_url = [NSString stringWithFormat:@"%@/search/json?query=%@", ATLAS_URL, query];
    [self postHttp: final_url :command :client_id :client_secret];
}

- (void)atlas_nearby:(CDVInvokedUrlCommand*)command
{
    [self setTime];
    NSString* client_id = [[command arguments] objectAtIndex:0];
    NSString* client_secret = [[command arguments] objectAtIndex:1];
    NSString* keyword = [[command arguments] objectAtIndex:2];
    NSString* refLocation = [[command arguments] objectAtIndex:3];
    NSString *final_url = [NSString stringWithFormat:@"%@/nearby/json?keywords=%@&refLocation=%@", ATLAS_URL, keyword, refLocation];
    [self postHttp: final_url :command :client_id :client_secret];
}

- (void)geocode:(CDVInvokedUrlCommand*)command
{
    NSString* key = [[command arguments] objectAtIndex:0];
    NSString* addr = [[command arguments] objectAtIndex:1];
    NSString *final_url = [NSString stringWithFormat:@"%@/%@/geo_code?addr=%@", BASE_REQ_URL,key, addr];
    [self getHttp: final_url :command];
}

- (void)revGeocode:(CDVInvokedUrlCommand*)command
{
    NSString* key = [[command arguments] objectAtIndex:0];
    NSString* lat = [[command arguments] objectAtIndex:1];
    NSString* lng = [[command arguments] objectAtIndex:2];
    NSString *final_url = [NSString stringWithFormat:@"%@/%@/rev_geocode?lat=%@&lng=%@", BASE_REQ_URL,key, lat, lng];
    [self getHttp: final_url :command];
}

- (void)placeDetail:(CDVInvokedUrlCommand*)command
{
    NSString* key = [[command arguments] objectAtIndex:0];
    NSString* placeId = [[command arguments] objectAtIndex:1];
    NSString *final_url = [NSString stringWithFormat:@"%@/%@/place_detail?place_id=%@", BASE_REQ_URL,key, placeId];
    [self getHttp: final_url :command];
}

- (void)setTime
{
    NSDate *now = [NSDate date];
    NSTimeInterval nowEpochSeconds = [now timeIntervalSince1970];
    current_time = (int) nowEpochSeconds;
}

- (void)getNearByPlaces:(CDVInvokedUrlCommand*)command
{
    NSString* key = [[command arguments] objectAtIndex:0];
    NSString* lat = [[command arguments] objectAtIndex:1];
    NSString* lng = [[command arguments] objectAtIndex:2];
    NSString* keywords = [[command arguments] objectAtIndex:3];
    NSString *final_url = [NSString stringWithFormat:@"%@/%@/nearby_search?lat=%@&lng=%@&keywords=%@&page=1", BASE_REQ_URL,key, lat, lng, keywords];
    [self getHttp: final_url :command];
}

- (void)getDistance:(CDVInvokedUrlCommand*)command
{
    NSString* key = [[command arguments] objectAtIndex:0];
    NSString* center_one = [[command arguments] objectAtIndex:1];
    NSString* center_two = [[command arguments] objectAtIndex:2];
    NSString* points = [[command arguments] objectAtIndex:3];
    NSString *final_url = [NSString stringWithFormat:@"%@/%@/distance?center=%@,%@&pts=%@", BASE_REQ_URL,key, center_one, center_two, points];
    [self getHttp: final_url :command];
}

- (void)Routing:(CDVInvokedUrlCommand*)command
{
    NSString* key = [[command arguments] objectAtIndex:0];
    NSString* start = [[command arguments] objectAtIndex:1];
    NSString* destination = [[command arguments] objectAtIndex:2];
    NSString* alternatives = [[command arguments] objectAtIndex:3];
    NSString* advices = [[command arguments] objectAtIndex:4];
    NSString *final_url = [NSString stringWithFormat:@"%@/%@/route?start=%@&destination=%@&alternatives=%@&with_advices=%@", BASE_REQ_URL,key, start,destination, alternatives, advices];
    [self getHttp: final_url :command];
}

  - (void) getHttp:(NSString *)url :(CDVInvokedUrlCommand *)command {
      NSMutableURLRequest *request = [[NSMutableURLRequest alloc] init];
      [request setURL:[NSURL URLWithString: url]];
      [request setHTTPMethod:@"GET"];

      NSURLSession *session = [NSURLSession sessionWithConfiguration:[NSURLSessionConfiguration defaultSessionConfiguration]];
      [[session dataTaskWithRequest:request completionHandler:^(NSData *data, NSURLResponse *response, NSError *error) {

      NSDictionary *json = [NSJSONSerialization JSONObjectWithData:data
                                                           options:kNilOptions
                                                             error:&error];
      if(!error){
        jsonResponse = json;
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary: jsonResponse];
      } else {
        jsonResponse = [NSDictionary dictionaryWithObjectsAndKeys:
                            @"400", @"status",
                            @"something went wrong/key is wrong/parameters are not valid", @"message", nil];
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsDictionary: jsonResponse];
      }

     [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];

  }] resume];
}

- (void)setOAuth2AccessToken:(NSString *)clientId :(NSString *)clientSecret {
      NSMutableURLRequest *urlRequest = [[NSMutableURLRequest alloc] initWithURL:[NSURL URLWithString: OUTPOST_URL]];
      [urlRequest setHTTPMethod:@"POST"];
      NSString *request =[NSString stringWithFormat:@"grant_type=client_credentials&client_id=%@&client_secret=%@", clientId, clientSecret];
      NSData *req_data = [request dataUsingEncoding:NSUTF8StringEncoding];
      [urlRequest setHTTPBody:req_data];
      NSURLSession *session = [NSURLSession sharedSession];
      NSURLSessionDataTask *dataTask = [session dataTaskWithRequest:urlRequest completionHandler:^(NSData *data, NSURLResponse *response, NSError *error) {
      NSError *parseError = nil;
      NSDictionary *responseObj = [NSJSONSerialization JSONObjectWithData:data options:0 error:&parseError];
      if([responseObj[@"error"] isEqualToString: @"invalid_client"]){
        NSLog(@"http code");
      } else {
          NSDate *now = [NSDate date];
          NSTimeInterval nowEpochSeconds = [now timeIntervalSince1970];
          current_time = (int) nowEpochSeconds;
          int expires_in_token =  (int)responseObj[@"expires_in"];
          int valid_till = (expires_in_token/1000) + (current_time);
          [[NSUserDefaults standardUserDefaults] setObject:responseObj[@"access_token"] forKey:@"access_token"];
          [[NSUserDefaults standardUserDefaults] setObject:[NSNumber numberWithInt:valid_till] forKey:@"access_token_validity"];
          [[NSUserDefaults standardUserDefaults] synchronize];
      }
  }];
  [dataTask resume];
}

- (void) postHttp:(NSString *)url :(CDVInvokedUrlCommand *)command :(NSString *)clientId :(NSString *)clientSecret {
      NSDate *now = [NSDate date];
      NSTimeInterval nowEpochSeconds = [now timeIntervalSince1970];
      current_time = (int) nowEpochSeconds;
      int validity = [[[NSUserDefaults standardUserDefaults] objectForKey:@"access_token_validity"] intValue];
      if (validity <= current_time) {
        [self setOAuth2AccessToken: clientId :clientSecret];
      }
      NSMutableURLRequest *request = [[NSMutableURLRequest alloc] init];
      [request setURL:[NSURL URLWithString: url]];
      [request setHTTPMethod:@"GET"];
      NSString *savedValue = [[NSUserDefaults standardUserDefaults] stringForKey:@"access_token"];
      [request setValue: [NSString stringWithFormat:@"%@ %@", oauth2_token_type, savedValue]  forHTTPHeaderField:@"Authorization"];
      NSURLSession *session = [NSURLSession sessionWithConfiguration:[NSURLSessionConfiguration defaultSessionConfiguration]];
      [[session dataTaskWithRequest:request completionHandler:^(NSData *data, NSURLResponse *response, NSError *error) {
      NSDictionary *responseObj = [NSJSONSerialization JSONObjectWithData:data
                                                             options:kNilOptions
                                                               error:&error];
       if(!error){
         jsonResponse = responseObj;
         pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary: jsonResponse];
       } else {
         jsonResponse = [NSDictionary dictionaryWithObjectsAndKeys:
                             @"400", @"status",
                             @"something went wrong", @"message", nil];
         pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsDictionary: jsonResponse];
       }
      [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
      }] resume];
}
@end
