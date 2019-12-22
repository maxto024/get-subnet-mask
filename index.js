import {NativeModules,Platform} from 'react-native';
module.exports=Platform.select({
    ios: NativeModules.CustomModule,
    android: NativeModules.MyNatievModule,
});
