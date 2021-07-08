# smart-crop
 图片智能裁剪，基于smartcrop.js开发，使用到作者QuadFlask：smartcrop-java项目的相关代码，集成到SpringBoot项目，方便使用

## 使用API

### 请求地址
```
POST http://localhost:9090/crop
```
### 请求参数(FormData)

| 属性     | 类型   | 必填 | 说明            |
| -------- | ------ | ---- | --------------- |
| imageUrl | string | 是   | 要检测的图片url |
| ratios   | string | 否   | 宽高比          |
| width    | number | 否   | 裁剪的宽度      |
| height   | number | 否   | 裁剪的高度      |

### 返回参数
| 属性     | 类型   | 说明            |
| -------- | ------| --------------- |
| crop | Object | 包括原点坐标xy，基于原点裁剪的width和height |
| image | Object | 包括图片的原始宽高（w/h） |

### 返回数据示例

```json
{
    "msg": "处理完成",
    "code": 0,
    "data": {
        "crop": {
            "x": 112,
            "y": 248,
            "width": 1080,
            "height": 1080
        },
        "image": {
            "w": 1200,
            "h": 1800
        }
    }
}
```




## 参考
 https://github.com/QuadFlask/smartcrop-java

 https://github.com/jwagner/smartcrop.js
