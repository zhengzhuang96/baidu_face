/*
 * @Author: zhengzhuang
 * @Date: 2020-12-03 16:10:45
 * @LastEditors: zhengzhuang
 * @LastEditTime: 2020-12-08 11:02:02
 * @Description: In User Settings Edit
 * @FilePath: /baidu_face/test/baidu_face_test.dart
 */
import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:baidu_face/baidu_face.dart';

void main() {
  const MethodChannel channel = MethodChannel('baidu_face');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    // expect(await BaiduFace.platformVersion, '42');
  });
}
