package utils;

import java.util.List;

/**
 * Created by Administrator on 2017/11/27.
 */

public class JavaBean {

    /**
     * status : 200
     * info : 成功
     * data : {"size":5,"list":[{"id":100000345,"title":"感到孤独给","image":"100000345_0.jpg","price":"66","contact":"王先生","head":"15138337441_HEAD.png","issue_time":"2017-08-11 01:36:52","follow":7,"state":1},{"id":100000344,"title":"哦哦你","image":"100000344_0.jpg","price":"111","contact":"1先生","head":"15630040707_HEAD.png","issue_time":"2017-08-11 00:55:33","follow":3,"state":1},{"id":100000328,"title":"666666","image":"100000328_0.jpg","price":"回家睡觉","contact":"W先生","head":"18538684101_HEAD.png","issue_time":"2017-08-09 03:26:57","follow":6,"state":1},{"id":100000327,"title":"666666","image":"100000327_0.jpg","price":"回家睡觉","contact":"W先生","head":"18538684101_HEAD.png","issue_time":"2017-08-09 03:26:07","follow":9,"state":1},{"id":100000326,"title":"明","image":"100000326_0.jpg","price":"12345679956","contact":"6先生","head":"18866666666_HEAD.png","issue_time":"2017-08-09 03:07:05","follow":3,"state":1}]}
     */

    private String status;
    private String info;
    private DataBean data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * size : 5
         * list : [{"id":100000345,"title":"感到孤独给","image":"100000345_0.jpg","price":"66","contact":"王先生","head":"15138337441_HEAD.png","issue_time":"2017-08-11 01:36:52","follow":7,"state":1},{"id":100000344,"title":"哦哦你","image":"100000344_0.jpg","price":"111","contact":"1先生","head":"15630040707_HEAD.png","issue_time":"2017-08-11 00:55:33","follow":3,"state":1},{"id":100000328,"title":"666666","image":"100000328_0.jpg","price":"回家睡觉","contact":"W先生","head":"18538684101_HEAD.png","issue_time":"2017-08-09 03:26:57","follow":6,"state":1},{"id":100000327,"title":"666666","image":"100000327_0.jpg","price":"回家睡觉","contact":"W先生","head":"18538684101_HEAD.png","issue_time":"2017-08-09 03:26:07","follow":9,"state":1},{"id":100000326,"title":"明","image":"100000326_0.jpg","price":"12345679956","contact":"6先生","head":"18866666666_HEAD.png","issue_time":"2017-08-09 03:07:05","follow":3,"state":1}]
         */

        private int size;
        private List<ListBean> list;

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 100000345
             * title : 感到孤独给
             * image : 100000345_0.jpg
             * price : 66
             * contact : 王先生
             * head : 15138337441_HEAD.png
             * issue_time : 2017-08-11 01:36:52
             * follow : 7
             * state : 1
             */

            private int id;
            private String title;
            private String image;
            private String price;
            private String contact;
            private String head;
            private String issue_time;
            private int follow;
            private int state;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getContact() {
                return contact;
            }

            public void setContact(String contact) {
                this.contact = contact;
            }

            public String getHead() {
                return head;
            }

            public void setHead(String head) {
                this.head = head;
            }

            public String getIssue_time() {
                return issue_time;
            }

            public void setIssue_time(String issue_time) {
                this.issue_time = issue_time;
            }

            public int getFollow() {
                return follow;
            }

            public void setFollow(int follow) {
                this.follow = follow;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }
        }
    }
}
