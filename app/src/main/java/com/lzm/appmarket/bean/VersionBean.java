package com.lzm.appmarket.bean;

import java.util.List;

public class VersionBean {
    /**
     * status : 1
     * time : 1514736000
     * version_in_review : 1.1.0717
     * msg : 当前版本为1.1.0717, 正式版,版本更新时间:2017-06-07 09:58:48
     * porker : {"enable":0,"download_ios":"1195483638","download_android":"","url_scheme_ios":"yiqigame","url_scheme_android":""}
     * banner : [{"banner_img":"http://dapai-image.oss-cn-shanghai.aliyuncs.com/img/2017-06-20/ru_jia.png","banner_url":"https://m.homeinns.com/?utm_source=pptf1&amp;utm_medium=app"}]
     */

    private int status;
    private String time;
    private String version_in_review;
    private String msg;
    /**
     * enable : 0
     * download_ios : 1195483638
     * download_android :
     * url_scheme_ios : yiqigame
     * url_scheme_android :
     */

    private PorkerEntity porker;
    /**
     * banner_img : http://dapai-image.oss-cn-shanghai.aliyuncs.com/img/2017-06-20/ru_jia.png
     * banner_url : https://m.homeinns.com/?utm_source=pptf1&amp;utm_medium=app
     */

    private List<BannerEntity> banner;

    public void setStatus(int status) {
        this.status = status;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setVersion_in_review(String version_in_review) {
        this.version_in_review = version_in_review;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setPorker(PorkerEntity porker) {
        this.porker = porker;
    }

    public void setBanner(List<BannerEntity> banner) {
        this.banner = banner;
    }

    public int getStatus() {
        return status;
    }

    public String getTime() {
        return time;
    }

    public String getVersion_in_review() {
        return version_in_review;
    }

    public String getMsg() {
        return msg;
    }

    public PorkerEntity getPorker() {
        return porker;
    }

    public List<BannerEntity> getBanner() {
        return banner;
    }

    public static class PorkerEntity {
        private int enable;
        private String download_ios;
        private String download_android;
        private String url_scheme_ios;
        private String url_scheme_android;

        public void setEnable(int enable) {
            this.enable = enable;
        }

        public void setDownload_ios(String download_ios) {
            this.download_ios = download_ios;
        }

        public void setDownload_android(String download_android) {
            this.download_android = download_android;
        }

        public void setUrl_scheme_ios(String url_scheme_ios) {
            this.url_scheme_ios = url_scheme_ios;
        }

        public void setUrl_scheme_android(String url_scheme_android) {
            this.url_scheme_android = url_scheme_android;
        }

        public int getEnable() {
            return enable;
        }

        public String getDownload_ios() {
            return download_ios;
        }

        public String getDownload_android() {
            return download_android;
        }

        public String getUrl_scheme_ios() {
            return url_scheme_ios;
        }

        public String getUrl_scheme_android() {
            return url_scheme_android;
        }
    }

    public static class BannerEntity {
        private String banner_img;
        private String banner_url;

        public void setBanner_img(String banner_img) {
            this.banner_img = banner_img;
        }

        public void setBanner_url(String banner_url) {
            this.banner_url = banner_url;
        }

        public String getBanner_img() {
            return banner_img;
        }

        public String getBanner_url() {
            return banner_url;
        }
    }
}
