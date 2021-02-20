# 社区APP后台开发路线规划
第一版（基本朋友圈）：
1. 用户注册(手机号)    √
2. 用户登录、退出      √
3. 用户信息修改(密码，昵称，头像，签名)
4. 发布动态、删除动态，带有坐标（图+文）
5. 查看指定用户基本信息和动态
6. 动态点赞，评论
7. 关注与被关注以及列表详情
8. 点赞通知（每小时汇总），评论通知（实时）
9. 系统通知。

第二版（完善社交基础体系）
1. 关注与被关注列表增加是否互关等披露信息
2. 用户主页增加照片封面
3. 用户主页增加点赞以及点赞列表详情  
4. 增加黑名单（无法看到对方动态）
5. 增加动态展示时间段设置（仅展示最近N天内的动态）
6. 增加邀请用户注册

第三版（完善IM体系）
1. 增加通讯录（互关的人可以加入通讯录）
2. 聊天面板中点击头像连接到对方主页
3. 黑名单机制完善（禁止黑名单中的人向我发送消息）
4. 增加会话列表，从会话列表进入一个聊天会话可以继续聊天

第四版（增加虚拟货币体系）
1. 通过微信或支付宝充值虚拟货币
2. 通过社区活动或其他合作渠道免费获得虚拟货币   
3. 虚拟货币可以通过购买虚拟礼品的方式用户之间相互赠送（入口在聊天面板和个人主页中）
4. 用户收到礼物后直接兑现成虚拟货币
5. 增加礼物赠送通知
6. 虚拟货币和安一定比率折现成人民币（慎重，反洗钱，同一个用户一天内折现不得超过1000元）

第五版（完善风控）
1. 新用户注册7天内且未充值不能收到礼物，充值或等待7天后取消限制。
2. 用户注册1个月内每天收入礼物不得超过100元，3个月内每天收入礼物不得超过5000元，半年内不能超过1000元。半年后取消限制。
3. 增加用户实名制，未实名时不得折现。
4. 用户单次体现超过一万元时，开始人工审核。
5. 增加用户举报功能。

第六版（增加担保交易模块）
类似咸鱼。
1. 用户允许发布商品
2. 用户购买商品时只允许通过虚拟货币购买，购买后冻结对应货币数。
3. 确认收货时，将冻结的货币划入对方账号，交易结束。

第七版（增加直播模块）
资金允许再考虑具体实现。

测试自动构建2