package com.qj.kaiyan.entitys;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Transient;

import java.io.Serializable;
import java.util.List;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class HomeResultItem implements Serializable {


    /**
     * author : {"approvedNotReadyVideoCount":0,"description":"匠心、健康、生活感悟","follow":{"followed":false,"itemId":2165,"itemType":"author"},"icon":"http://img.kaiyanapp.com/924ebc6780d59925c8a346a5dafc90bb.jpeg","id":2165,"ifPgc":true,"latestReleaseTime":1527469203000,"link":"","name":"开眼生活精选","shield":{"itemId":2165,"itemType":"author","shielded":false},"videoNum":100}
     * category : 生活
     * collected : false
     * consumption : {"collectionCount":25,"replyCount":0,"shareCount":18}
     * cover : {"blurred":"http://img.kaiyanapp.com/6d687fd2db9b02c5453d84f8ae163669.jpeg?imageMogr2/quality/60/format/jpg","detail":"http://img.kaiyanapp.com/0c4e71106afc8f27d0e3ac5301360ef6.jpeg?imageMogr2/quality/60/format/jpg","feed":"http://img.kaiyanapp.com/0c4e71106afc8f27d0e3ac5301360ef6.jpeg?imageMogr2/quality/60/format/jpg","homepage":"http://img.kaiyanapp.com/0c4e71106afc8f27d0e3ac5301360ef6.jpeg?imageView2/1/w/720/h/560/format/jpg/q/75|watermark/1/image/aHR0cDovL2ltZy5rYWl5YW5hcHAuY29tL2JsYWNrXzMwLnBuZw==/dissolve/100/gravity/Center/dx/0/dy/0|imageslim"}
     * dataType : VideoBeanForClient
     * date : 1527469200000
     * description : 本片是根据摄影师兼作家同时也是片中女主 Sarah Eiseman 的亲身经历创作而成。讨论了人生中不断经历的失去和挫折，我们会害怕但也很勇敢，其实到最后我们会发现脆弱和敏感才是人生的美好之处。From Joe Simon
     * descriptionEditor : 本片是根据摄影师兼作家同时也是片中女主 Sarah Eiseman 的亲身经历创作而成。讨论了人生中不断经历的失去和挫折，我们会害怕但也很勇敢，其实到最后我们会发现脆弱和敏感才是人生的美好之处。From Joe Simon
     * duration : 200
     * id : 12626
     * idx : 0
     * ifLimitVideo : false
     * labelList : []
     * library : DAILY
     * playInfo : [{"height":480,"name":"标清","type":"normal","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=12626&resourceType=video&editionType=normal&source=aliyun","urlList":[{"name":"aliyun","size":11430787,"url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=12626&resourceType=video&editionType=normal&source=aliyun"},{"name":"qcloud","size":11430787,"url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=12626&resourceType=video&editionType=normal&source=qcloud"},{"name":"ucloud","size":11430787,"url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=12626&resourceType=video&editionType=normal&source=ucloud"}],"width":854},{"height":720,"name":"高清","type":"high","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=12626&resourceType=video&editionType=high&source=aliyun","urlList":[{"name":"aliyun","size":17079801,"url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=12626&resourceType=video&editionType=high&source=aliyun"},{"name":"qcloud","size":17079801,"url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=12626&resourceType=video&editionType=high&source=qcloud"},{"name":"ucloud","size":17079801,"url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=12626&resourceType=video&editionType=high&source=ucloud"}],"width":1280}]
     * playUrl : http://baobab.kaiyanapp.com/api/v1/playUrl?vid=12626&resourceType=video&editionType=default&source=aliyun
     * played : false
     * provider : {"alias":"youtube","icon":"http://img.kaiyanapp.com/fa20228bc5b921e837156923a58713f6.png","name":"YouTube"}
     * releaseTime : 1527469203000
     * resourceType : video
     * searchWeight : 0
     * slogan : 世间万物，皆有感觉
     * subtitles : []
     * tags : [{"actionUrl":"eyepetizer://tag/530/?title=%E4%BA%BA%E7%94%9F","bgPicture":"http://img.kaiyanapp.com/a57744110ddbaa1e99d148a01c1b1bd8.jpeg?imageMogr2/quality/60/format/jpg","headerImage":"http://img.kaiyanapp.com/a57744110ddbaa1e99d148a01c1b1bd8.jpeg?imageMogr2/quality/60/format/jpg","id":530,"name":"人生","tagRecType":"NORMAL"},{"actionUrl":"eyepetizer://tag/482/?title=%E6%84%9F%E6%82%9F","bgPicture":"http://img.kaiyanapp.com/5ae529b018ada5073d486242afc855b7.jpeg?imageMogr2/quality/60/format/jpg","headerImage":"http://img.kaiyanapp.com/5ae529b018ada5073d486242afc855b7.jpeg?imageMogr2/quality/60/format/jpg","id":482,"name":"感悟","tagRecType":"NORMAL"},{"actionUrl":"eyepetizer://tag/98/?title=%E5%BC%80%E6%94%BE%E5%BC%8F%E7%BB%93%E5%B1%80","bgPicture":"http://img.kaiyanapp.com/2a20b01c5110c2d6602835c8c4fdc357.jpeg?imageMogr2/quality/100","headerImage":"http://img.kaiyanapp.com/2a20b01c5110c2d6602835c8c4fdc357.jpeg?imageMogr2/quality/100","id":98,"name":"开放式结局","tagRecType":"NORMAL"},{"actionUrl":"eyepetizer://tag/666/?title=%E7%94%9F%E6%B4%BB","bgPicture":"http://img.kaiyanapp.com/be945b24182e6a268bdd66f054148f47.jpeg?imageMogr2/quality/60/format/jpg","headerImage":"http://img.kaiyanapp.com/be945b24182e6a268bdd66f054148f47.jpeg?imageMogr2/quality/60/format/jpg","id":666,"name":"生活","tagRecType":"NORMAL"}]
     * title : 失去过，生命也许更加精彩
     * type : NORMAL
     * webUrl : {"forWeibo":"http://wandou.im/3lbqt1","raw":"http://www.eyepetizer.net/detail.html?vid=12626"}
     */
    private static final long serialVersionUID = 9527l;

    @Id(autoincrement = true)
    private Long _id;
    @Transient
    private AuthorBean author;
    @Property
    private String category;
    @Transient
    private boolean collected;
    @Transient
    private ConsumptionBean consumption;
    @Property
    @Convert(converter = CoverCoverter.class,columnType = String.class)
    private CoverBean cover;
    @Transient
    private String dataType;
    @Transient
    private long date;
    @Transient
    private String description;
    @Transient
    private String descriptionEditor;
    @Property
    private int duration;
    @Transient
    private int id;
    @Transient
    private int idx;
    @Transient
    private boolean ifLimitVideo;
    @Transient
    private String library;

    @Property
    private String playUrl;
    @Transient
    private boolean played;
    @Transient
    private ProviderBean provider;
    @Transient
    private long releaseTime;
    @Transient
    private String resourceType;
    @Transient
    private int searchWeight;
    @Transient
    private String slogan;
    @Property
    private String title;
    @Transient
    private String type;
    @Transient
    private WebUrlBean webUrl;
    @Transient
    private List<?> labelList;
    @Transient
    private List<PlayInfoBean> playInfo;
    @Transient
    private List<?> subtitles;
    @Transient
    private List<TagsBean> tags;
    @Transient
    private String image;
    @Transient
    private String actionUrl;
    @Transient
    private Object adTrack;
    @Transient
    private boolean shade;
    @Transient
    private Object label;
    @Transient
    private Object header;

    @Generated(hash = 176983403)
    public HomeResultItem(Long _id, String category, CoverBean cover, int duration, String playUrl, String title) {
        this._id = _id;
        this.category = category;
        this.cover = cover;
        this.duration = duration;
        this.playUrl = playUrl;
        this.title = title;
    }

    @Generated(hash = 1355850364)
    public HomeResultItem() {
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getActionUrl() {
        return actionUrl;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }

    public Object getAdTrack() {
        return adTrack;
    }

    public void setAdTrack(Object adTrack) {
        this.adTrack = adTrack;
    }

    public boolean isShade() {
        return shade;
    }

    public void setShade(boolean shade) {
        this.shade = shade;
    }

    public Object getLabel() {
        return label;
    }

    public void setLabel(Object label) {
        this.label = label;
    }

    public Object getHeader() {
        return header;
    }

    public void setHeader(Object header) {
        this.header = header;
    }

    public AuthorBean getAuthor() {
        return author;
    }

    public void setAuthor(AuthorBean author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isCollected() {
        return collected;
    }

    public void setCollected(boolean collected) {
        this.collected = collected;
    }

    public ConsumptionBean getConsumption() {
        return consumption;
    }

    public void setConsumption(ConsumptionBean consumption) {
        this.consumption = consumption;
    }

    public CoverBean getCover() {
        return cover;
    }

    public void setCover(CoverBean cover) {
        this.cover = cover;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionEditor() {
        return descriptionEditor;
    }

    public void setDescriptionEditor(String descriptionEditor) {
        this.descriptionEditor = descriptionEditor;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public boolean isIfLimitVideo() {
        return ifLimitVideo;
    }

    public void setIfLimitVideo(boolean ifLimitVideo) {
        this.ifLimitVideo = ifLimitVideo;
    }

    public String getLibrary() {
        return library;
    }

    public void setLibrary(String library) {
        this.library = library;
    }

    public String getPlayUrl() {
        return playUrl;
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    public boolean isPlayed() {
        return played;
    }

    public void setPlayed(boolean played) {
        this.played = played;
    }

    public ProviderBean getProvider() {
        return provider;
    }

    public void setProvider(ProviderBean provider) {
        this.provider = provider;
    }

    public long getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(long releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public int getSearchWeight() {
        return searchWeight;
    }

    public void setSearchWeight(int searchWeight) {
        this.searchWeight = searchWeight;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public WebUrlBean getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(WebUrlBean webUrl) {
        this.webUrl = webUrl;
    }

    public List<?> getLabelList() {
        return labelList;
    }

    public void setLabelList(List<?> labelList) {
        this.labelList = labelList;
    }

    public List<PlayInfoBean> getPlayInfo() {
        return playInfo;
    }

    public void setPlayInfo(List<PlayInfoBean> playInfo) {
        this.playInfo = playInfo;
    }

    public List<?> getSubtitles() {
        return subtitles;
    }

    public void setSubtitles(List<?> subtitles) {
        this.subtitles = subtitles;
    }

    public List<TagsBean> getTags() {
        return tags;
    }

    public void setTags(List<TagsBean> tags) {
        this.tags = tags;
    }

    public static class AuthorBean implements Serializable {
        /**
         * approvedNotReadyVideoCount : 0
         * description : 匠心、健康、生活感悟
         * follow : {"followed":false,"itemId":2165,"itemType":"author"}
         * icon : http://img.kaiyanapp.com/924ebc6780d59925c8a346a5dafc90bb.jpeg
         * id : 2165
         * ifPgc : true
         * latestReleaseTime : 1527469203000
         * link :
         * name : 开眼生活精选
         * shield : {"itemId":2165,"itemType":"author","shielded":false}
         * videoNum : 100
         */
        private static final long serialVersionUID = 9528l;
        private int approvedNotReadyVideoCount;
        private String description;
        private FollowBean follow;
        private String icon;
        private int id;
        private boolean ifPgc;
        private long latestReleaseTime;
        private String link;
        private String name;
        private ShieldBean shield;
        private int videoNum;

        public int getApprovedNotReadyVideoCount() {
            return approvedNotReadyVideoCount;
        }

        public void setApprovedNotReadyVideoCount(int approvedNotReadyVideoCount) {
            this.approvedNotReadyVideoCount = approvedNotReadyVideoCount;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public FollowBean getFollow() {
            return follow;
        }

        public void setFollow(FollowBean follow) {
            this.follow = follow;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public boolean isIfPgc() {
            return ifPgc;
        }

        public void setIfPgc(boolean ifPgc) {
            this.ifPgc = ifPgc;
        }

        public long getLatestReleaseTime() {
            return latestReleaseTime;
        }

        public void setLatestReleaseTime(long latestReleaseTime) {
            this.latestReleaseTime = latestReleaseTime;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public ShieldBean getShield() {
            return shield;
        }

        public void setShield(ShieldBean shield) {
            this.shield = shield;
        }

        public int getVideoNum() {
            return videoNum;
        }

        public void setVideoNum(int videoNum) {
            this.videoNum = videoNum;
        }

        public static class FollowBean implements Serializable {
            /**
             * followed : false
             * itemId : 2165
             * itemType : author
             */
            private static final long serialVersionUID = 9535l;
            private boolean followed;
            private int itemId;
            private String itemType;

            public boolean isFollowed() {
                return followed;
            }

            public void setFollowed(boolean followed) {
                this.followed = followed;
            }

            public int getItemId() {
                return itemId;
            }

            public void setItemId(int itemId) {
                this.itemId = itemId;
            }

            public String getItemType() {
                return itemType;
            }

            public void setItemType(String itemType) {
                this.itemType = itemType;
            }
        }

        public static class ShieldBean implements Serializable {
            /**
             * itemId : 2165
             * itemType : author
             * shielded : false
             */
            private static final long serialVersionUID = 9536l;
            private int itemId;
            private String itemType;
            private boolean shielded;

            public int getItemId() {
                return itemId;
            }

            public void setItemId(int itemId) {
                this.itemId = itemId;
            }

            public String getItemType() {
                return itemType;
            }

            public void setItemType(String itemType) {
                this.itemType = itemType;
            }

            public boolean isShielded() {
                return shielded;
            }

            public void setShielded(boolean shielded) {
                this.shielded = shielded;
            }
        }
    }

    public static class ConsumptionBean implements Serializable {
        /**
         * collectionCount : 25
         * replyCount : 0
         * shareCount : 18
         */
        private static final long serialVersionUID = 9529l;
        private int collectionCount;
        private int replyCount;
        private int shareCount;

        public int getCollectionCount() {
            return collectionCount;
        }

        public void setCollectionCount(int collectionCount) {
            this.collectionCount = collectionCount;
        }

        public int getReplyCount() {
            return replyCount;
        }

        public void setReplyCount(int replyCount) {
            this.replyCount = replyCount;
        }

        public int getShareCount() {
            return shareCount;
        }

        public void setShareCount(int shareCount) {
            this.shareCount = shareCount;
        }
    }

    public static class CoverBean implements Serializable {
        /**
         * blurred : http://img.kaiyanapp.com/6d687fd2db9b02c5453d84f8ae163669.jpeg?imageMogr2/quality/60/format/jpg
         * detail : http://img.kaiyanapp.com/0c4e71106afc8f27d0e3ac5301360ef6.jpeg?imageMogr2/quality/60/format/jpg
         * feed : http://img.kaiyanapp.com/0c4e71106afc8f27d0e3ac5301360ef6.jpeg?imageMogr2/quality/60/format/jpg
         * homepage : http://img.kaiyanapp.com/0c4e71106afc8f27d0e3ac5301360ef6.jpeg?imageView2/1/w/720/h/560/format/jpg/q/75|watermark/1/image/aHR0cDovL2ltZy5rYWl5YW5hcHAuY29tL2JsYWNrXzMwLnBuZw==/dissolve/100/gravity/Center/dx/0/dy/0|imageslim
         */
        private static final long serialVersionUID = 9530l;
        private String blurred;
        private String detail;
        private String feed;
        private String homepage;

        public String getBlurred() {
            return blurred;
        }

        public void setBlurred(String blurred) {
            this.blurred = blurred;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getFeed() {
            return feed;
        }

        public void setFeed(String feed) {
            this.feed = feed;
        }

        public String getHomepage() {
            return homepage;
        }

        public void setHomepage(String homepage) {
            this.homepage = homepage;
        }
    }

    public static class ProviderBean implements Serializable {
        /**
         * alias : youtube
         * icon : http://img.kaiyanapp.com/fa20228bc5b921e837156923a58713f6.png
         * name : YouTube
         */
        private static final long serialVersionUID = 9531l;
        private String alias;
        private String icon;
        private String name;

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class WebUrlBean implements Serializable {
        /**
         * forWeibo : http://wandou.im/3lbqt1
         * raw : http://www.eyepetizer.net/detail.html?vid=12626
         */
        private static final long serialVersionUID = 9532l;
        private String forWeibo;
        private String raw;

        public String getForWeibo() {
            return forWeibo;
        }

        public void setForWeibo(String forWeibo) {
            this.forWeibo = forWeibo;
        }

        public String getRaw() {
            return raw;
        }

        public void setRaw(String raw) {
            this.raw = raw;
        }
    }

    public static class PlayInfoBean implements Serializable {
        /**
         * height : 480
         * name : 标清
         * type : normal
         * url : http://baobab.kaiyanapp.com/api/v1/playUrl?vid=12626&resourceType=video&editionType=normal&source=aliyun
         * urlList : [{"name":"aliyun","size":11430787,"url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=12626&resourceType=video&editionType=normal&source=aliyun"},{"name":"qcloud","size":11430787,"url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=12626&resourceType=video&editionType=normal&source=qcloud"},{"name":"ucloud","size":11430787,"url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=12626&resourceType=video&editionType=normal&source=ucloud"}]
         * width : 854
         */
        private static final long serialVersionUID = 9533l;
        private int height;
        private String name;
        private String type;
        private String url;
        private int width;
        private List<UrlListBean> urlList;

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public List<UrlListBean> getUrlList() {
            return urlList;
        }

        public void setUrlList(List<UrlListBean> urlList) {
            this.urlList = urlList;
        }

        public static class UrlListBean implements Serializable {
            /**
             * name : aliyun
             * size : 11430787
             * url : http://baobab.kaiyanapp.com/api/v1/playUrl?vid=12626&resourceType=video&editionType=normal&source=aliyun
             */

            private String name;
            private int size;
            private String url;
            private static final long serialVersionUID = 9537l;
            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getSize() {
                return size;
            }

            public void setSize(int size) {
                this.size = size;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }

    public static class TagsBean implements Serializable {
        /**
         * actionUrl : eyepetizer://tag/530/?title=%E4%BA%BA%E7%94%9F
         * bgPicture : http://img.kaiyanapp.com/a57744110ddbaa1e99d148a01c1b1bd8.jpeg?imageMogr2/quality/60/format/jpg
         * headerImage : http://img.kaiyanapp.com/a57744110ddbaa1e99d148a01c1b1bd8.jpeg?imageMogr2/quality/60/format/jpg
         * id : 530
         * name : 人生
         * tagRecType : NORMAL
         */
        private static final long serialVersionUID = 9534l;
        private String actionUrl;
        private String bgPicture;
        private String headerImage;
        private int id;
        private String name;
        private String tagRecType;

        public String getActionUrl() {
            return actionUrl;
        }

        public void setActionUrl(String actionUrl) {
            this.actionUrl = actionUrl;
        }

        public String getBgPicture() {
            return bgPicture;
        }

        public void setBgPicture(String bgPicture) {
            this.bgPicture = bgPicture;
        }

        public String getHeaderImage() {
            return headerImage;
        }

        public void setHeaderImage(String headerImage) {
            this.headerImage = headerImage;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTagRecType() {
            return tagRecType;
        }

        public void setTagRecType(String tagRecType) {
            this.tagRecType = tagRecType;
        }
    }


}
