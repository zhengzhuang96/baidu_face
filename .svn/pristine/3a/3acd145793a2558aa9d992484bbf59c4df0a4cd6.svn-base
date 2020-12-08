// 人脸license文件名
#define FACE_LICENSE_NAME    @"idl-license"

// 人脸license后缀
#define FACE_LICENSE_SUFFIX  @"face-ios"

#import "BaiduFacePlugin.h"
#import <IDLFaceSDK/IDLFaceSDK.h>
#import "LivenessViewController.h"

@implementation BaiduFacePlugin {
    NSNumber* _livenessRandom;
    NSArray<NSNumber*>* _livenessTypeList;
}

+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
    FlutterMethodChannel* channel = [FlutterMethodChannel
                                     methodChannelWithName:@"baidu_face"
                                     binaryMessenger:[registrar messenger]];
    BaiduFacePlugin* instance = [[BaiduFacePlugin alloc] init];
    [registrar addMethodCallDelegate:instance channel:channel];
}

- (void)handleMethodCall:(FlutterMethodCall*)call result:(FlutterResult)result {
    NSDictionary<NSString*, id>* args = call.arguments;
    if ([@"init" isEqualToString:call.method]) {
        NSString* licenseID = args[@"licenseID"];
        _livenessTypeList = (NSArray<NSNumber*>*) args[@"livenessTypeList"];
        _livenessRandom = args[@"livenessRandom"];
        NSNumber* blurnessValue = args[@"blurnessValue"];
        NSNumber* brightnessValue = args[@"brightnessValue"];
        NSNumber* cropFaceValue = args[@"cropFaceValue"];
        NSNumber* headPitchValue = args[@"headPitchValue"];
        NSNumber* headRollValue = args[@"headRollValue"];
        NSNumber* headYawValue = args[@"headYawValue"];
        NSNumber* minFaceSize = args[@"minFaceSize"];
        NSNumber* notFaceSize = args[@"notFaceSize"];
        NSNumber* occlusionValue = args[@"occlusionValue"];
        //        NSNumber* threadNumber = args[@"threadNumber"];
        NSNumber* sound = args[@"sound"];
        
        // 设置鉴权
        NSString* licensePath = [NSString stringWithFormat:@"%@.%@", FACE_LICENSE_NAME, FACE_LICENSE_SUFFIX ];
        
        [[FaceSDKManager sharedInstance] setLicenseID:licenseID andLocalLicenceFile:licensePath andRemoteAuthorize:false];
        
        NSLog(@"canWork = %d",[[FaceSDKManager sharedInstance] canWork]);
        
        if (minFaceSize != nil && (NSNull*) minFaceSize != [NSNull null])
            [[FaceSDKManager sharedInstance] setMinFaceSize:[minFaceSize intValue]];
        if (blurnessValue != nil && (NSNull*) blurnessValue != [NSNull null])
            [[FaceSDKManager sharedInstance] setBlurThreshold:[blurnessValue floatValue]];
        if (brightnessValue != nil && (NSNull*) brightnessValue != [NSNull null])
            [[FaceSDKManager sharedInstance] setIllumThreshold:[brightnessValue intValue]];
        if (occlusionValue != nil && (NSNull*) occlusionValue != [NSNull null])
            [[FaceSDKManager sharedInstance] setOccluThreshold:[occlusionValue floatValue]];
        if (cropFaceValue != nil && (NSNull*) cropFaceValue != [NSNull null])
            [[FaceSDKManager sharedInstance] setMaxCropImageNum:[cropFaceValue intValue]];
        if ((headPitchValue != nil && (NSNull*) headPitchValue != [NSNull null])
            || (headYawValue != nil && (NSNull*) headYawValue != [NSNull null])
            || (headRollValue != nil && (NSNull*) headRollValue != [NSNull null]))
            [[FaceSDKManager sharedInstance] setEulurAngleThrPitch:[headPitchValue intValue]
                                                               yaw:[headYawValue intValue]
                                                              roll:[headRollValue intValue]];
        if (notFaceSize != nil && (NSNull*) notFaceSize != [NSNull null])
            [[FaceSDKManager sharedInstance] setNotFaceThreshold:[notFaceSize floatValue]];
        if (sound != nil && (NSNull*) sound != [NSNull null])
            [[IDLFaceLivenessManager sharedInstance] setEnableSound:[sound boolValue]];
        [[FaceSDKManager sharedInstance] initCollect];
        
        result(@"success");
    } else if ([@"liveDetect" isEqualToString:call.method]) {
        
        LivenessViewController* lvc = [[LivenessViewController alloc] init];
        [lvc livenesswithList:_livenessTypeList
                        order:!_livenessRandom
             numberOfLiveness:1];
        lvc.userName = userName;
        lvc.cardNum = cardNum;
        lvc.completion = ^(NSDictionary* images, UIImage* originImage){
            NSArray *imageArr = images[@"image"];
            FaceCropImageInfo * bestImage = imageArr[0];
            NSString *imagestr = bestImage.cropImageWithBlackEncryptStr;
            if (imagestr != nil) {
                result(imagestr);
            } else {
                result([FlutterError errorWithCode:@"识别失败" message:@"识别失败" details:@"识别失败"]);
            }
        };
        [[UIApplication sharedApplication].keyWindow.rootViewController presentViewController:lvc animated:YES completion:nil];
        
    } else if ([@"uninit" isEqualToString:call.method]){
        [[FaceSDKManager sharedInstance] uninitCollect];
    } else {
        result(FlutterMethodNotImplemented);
    }
}

@end
