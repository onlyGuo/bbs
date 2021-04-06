create table bbs_comment
(
    id              bigint auto_increment
        primary key,
    user_id         int           null comment '评论用户ID',
    user_nicker     varchar(20)   null comment '发布人昵称',
    user_head_img   varchar(255)  null comment '发布人头像',
    post_id         bigint        null comment '所属帖子ID',
    comment_id      bigint        null comment '回复评论ID',
    create_time     datetime      null comment '评论时间',
    comment_content varchar(255)  null comment '评论内容',
    love_count      int           null comment '点赞/喜欢数量',
    hate_count      int           null comment '踩/讨厌数量',
    status          int default 0 null comment '0 = 正常，-1 = 被删除'
)
    comment '帖子评论表';

create table bbs_feedback
(
    id          int auto_increment
        primary key,
    user_id     int          null comment '用户ID',
    create_time datetime     null comment '反馈时间',
    content     varchar(200) null comment '反馈内容',
    phone       varchar(50)  null comment '联系方式'
)
    comment '意见反馈表';

create table bbs_post
(
    id            bigint auto_increment
        primary key,
    user_id       int           null comment '发布人ID',
    user_nicker   varchar(100)  null comment '发布人昵称',
    user_head_img varchar(255)  null comment '发布人头像',
    type          int default 0 null comment '0 = 普通动态（文字+图片）， 1 = 文章',
    title         varchar(100)  null comment '帖子标题（type=1时有效）',
    content       text          null comment '帖子内容',
    imgs          json          null comment '帖子图片：{["xxxx.jpg", "xxx.jpg"]}
type=0时有效',
    create_time   datetime      null comment '发布时间',
    love_count    int default 0 null comment '喜欢/点赞数量',
    comment_count int default 0 null comment '评论数量',
    lon           double        null comment '经度',
    lat           double        null comment '纬度',
    city_name     varchar(20)   null comment '城市名称',
    deleted       int default 0 null,
    top           int default 0 null,
    tags          json          null comment '标签'
);

create table bbs_post_love
(
    id      bigint auto_increment
        primary key,
    post_id bigint        null comment '帖子ID',
    user_id int           null comment '用户ID',
    love    int default 1 null
);

create table bbs_post_message
(
    id              bigint auto_increment
        primary key,
    post_id         bigint        null comment '帖子ID',
    comment_id      bigint        null comment '评论ID',
    author_user_id  int           null comment '帖子作者用户ID',
    user_id         int           null comment '消息来源用户ID',
    user_nicker     varchar(50)   null comment '消息来源用户昵称',
    user_header_img varchar(255)  null comment '来源用户头像',
    type            int           null comment '0 = 艾特， 1 = 评论， 2 = 点赞',
    `read`          int           null comment '作者是否已读',
    content         varchar(1024) null,
    create_time     datetime      null comment '创建时间'
)
    comment '帖子消息';

create table bbs_tag
(
    id        int auto_increment
        primary key,
    name      varchar(40)   null comment '标签名称',
    anonymous int default 0 null comment '是否匿名',
    rule      int           null comment '什么角色可以发布'
)
    comment '帖子标签';

INSERT INTO bbs.bbs_tag (id, name, anonymous, rule) VALUES (1, '心情瞬间', 0, 0);
INSERT INTO bbs.bbs_tag (id, name, anonymous, rule) VALUES (2, '求助问答', 0, 0);
INSERT INTO bbs.bbs_tag (id, name, anonymous, rule) VALUES (3, '新闻咨询', 0, 2);

create table bbs_user
(
    id       int auto_increment
        primary key,
    phone    varchar(50)  null comment '账号/手机号',
    nicker   varchar(20)  null comment '用户昵称',
    password varchar(32)  null comment '用户密码',
    sign     varchar(100) null comment '个性签名',
    head_img varchar(255) null comment '用户头像',
    rule     int          null comment '0 = 普通用户， 1 = 内部， 2 = 记者， 3 = 超管'
)
    comment '用户表';

INSERT INTO bbs.bbs_user (id, phone, nicker, password, sign, head_img, rule) VALUES (1, 'admin', '郭胜凯', '4297f44b13955235245b2497399d7a93', null, null, 3);

create table bbs_user_token
(
    id      int auto_increment
        primary key,
    user_id int         null,
    token   varchar(50) null
);

