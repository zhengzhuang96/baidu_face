/*
 * @Author: your name
 * @Date: 2020-12-04 09:05:02
 * @LastEditTime: 2020-12-07 16:57:08
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: /baidu_face/example/lib/main.dart
 */

import 'dart:convert';
import 'dart:io';

import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:baidu_face/baidu_face.dart';
import 'package:baidu_face/config.dart';
import 'package:baidu_face/enums.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  // 人脸识别初始化
  final config = Config(
      livenessTypeList: [LivenessType.Eye],);
  if (Platform.isAndroid) {
    await BaiduFace.instance.init('driver-03-face-android', config: config);
  } else if (Platform.isIOS) {
    await BaiduFace.instance.init('driver-03-face-ios', config: config);
  }
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Title',
      home: HomePage(),
    );
  }
}

class HomePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('百度离线活体SDK_flutter'),
      ),
      body: Center(
        child: FlatButton(
          onPressed: () => _handleVerify(context),
          child: Text('开始验证'),
        ),
      ),
    );
  }

  void _handleVerify(BuildContext context) async {
    try {
      final base64Image = await BaiduFace.instance.liveDetect();
      showDialog(
        context: context,
        barrierDismissible: true,
        builder: (context) => Dialog(
          child: Image.memory(base64Decode(base64Image)),
        ),
      );
    } on PlatformException catch (e) {
      debugPrint(e.message);
    }
  }
}
