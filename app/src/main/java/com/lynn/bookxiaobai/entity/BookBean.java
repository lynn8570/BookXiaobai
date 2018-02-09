package com.lynn.bookxiaobai.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Administrator on 2018/2/7.
 */

public class BookBean implements Parcelable {



    private RatingBean rating;
    private String subtitle;
    private String pubdate;
    private String origin_title;
    private String image;
    private String binding;
    private String catalog;
    private String pages;
    private ImagesBean images;
    private String alt;
    private String id;
    private String publisher;
    private String isbn10;
    private String isbn13;
    private String title;
    private String url;
    private String alt_title;
    private String author_intro;
    private String summary;
    private String price;
    private List<String> author;
    private List<TagsBean> tags;
    private List<String> translator;

    private UserNote userNote;//linlian added for user note

    public UserNote getUserNote() {
        return userNote;
    }

    public void setUserNote(UserNote userNote) {
        this.userNote = userNote;
    }

    public RatingBean getRating() {
        return rating;
    }

    public void setRating(RatingBean rating) {
        this.rating = rating;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getOrigin_title() {
        return origin_title;
    }

    public void setOrigin_title(String origin_title) {
        this.origin_title = origin_title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBinding() {
        return binding;
    }

    public void setBinding(String binding) {
        this.binding = binding;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public ImagesBean getImages() {
        return images;
    }

    public void setImages(ImagesBean images) {
        this.images = images;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAlt_title() {
        return alt_title;
    }

    public void setAlt_title(String alt_title) {
        this.alt_title = alt_title;
    }

    public String getAuthor_intro() {
        return author_intro;
    }

    public void setAuthor_intro(String author_intro) {
        this.author_intro = author_intro;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<String> getAuthor() {
        return author;
    }

    public void setAuthor(List<String> author) {
        this.author = author;
    }

    public List<TagsBean> getTags() {
        return tags;
    }

    public void setTags(List<TagsBean> tags) {
        this.tags = tags;
    }

    public List<String> getTranslator() {
        return translator;
    }

    public void setTranslator(List<String> translator) {
        this.translator = translator;
    }

    public static class UserNote implements Parcelable {
        public static final int STATE_UNSTART=0;
        public static final int STATE_UNREADED=1;
        public static final int STATE_READED=2;

        private int state;
        private long stateTime;
        private String note;
        private String noteTime;

        public long getStateTime() {
            return stateTime;
        }

        public void setStateTime(long stateTime) {
            this.stateTime = stateTime;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public String getNoteTime() {
            return noteTime;
        }

        public void setNoteTime(String noteTime) {
            this.noteTime = noteTime;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.state);
            dest.writeLong(this.stateTime);
            dest.writeString(this.note);
            dest.writeString(this.noteTime);
        }

        public UserNote() {
        }

        protected UserNote(Parcel in) {
            this.state = in.readInt();
            this.stateTime = in.readLong();
            this.note = in.readString();
            this.noteTime = in.readString();
        }

        public static final Creator<UserNote> CREATOR = new Creator<UserNote>() {
            @Override
            public UserNote createFromParcel(Parcel source) {
                return new UserNote(source);
            }

            @Override
            public UserNote[] newArray(int size) {
                return new UserNote[size];
            }
        };
    }

    public static class RatingBean implements Parcelable {
        /**
         * max : 10
         * numRaters : 11469
         * average : 9.2
         * min : 0
         */

        private int max;
        private int numRaters;
        private String average;
        private int min;

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public int getNumRaters() {
            return numRaters;
        }

        public void setNumRaters(int numRaters) {
            this.numRaters = numRaters;
        }

        public String getAverage() {
            return average;
        }

        public void setAverage(String average) {
            this.average = average;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.max);
            dest.writeInt(this.numRaters);
            dest.writeString(this.average);
            dest.writeInt(this.min);
        }

        public RatingBean() {
        }

        protected RatingBean(Parcel in) {
            this.max = in.readInt();
            this.numRaters = in.readInt();
            this.average = in.readString();
            this.min = in.readInt();
        }

        public static final Creator<RatingBean> CREATOR = new Creator<RatingBean>() {
            @Override
            public RatingBean createFromParcel(Parcel source) {
                return new RatingBean(source);
            }

            @Override
            public RatingBean[] newArray(int size) {
                return new RatingBean[size];
            }
        };
    }

    public static class ImagesBean implements Parcelable {
        /**
         * small : https://img3.doubanio.com/spic/s3294754.jpg
         * large : https://img3.doubanio.com/lpic/s3294754.jpg
         * medium : https://img3.doubanio.com/mpic/s3294754.jpg
         */

        private String small;
        private String large;
        private String medium;

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getLarge() {
            return large;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.small);
            dest.writeString(this.large);
            dest.writeString(this.medium);
        }

        public ImagesBean() {
        }

        protected ImagesBean(Parcel in) {
            this.small = in.readString();
            this.large = in.readString();
            this.medium = in.readString();
        }

        public static final Creator<ImagesBean> CREATOR = new Creator<ImagesBean>() {
            @Override
            public ImagesBean createFromParcel(Parcel source) {
                return new ImagesBean(source);
            }

            @Override
            public ImagesBean[] newArray(int size) {
                return new ImagesBean[size];
            }
        };
    }

    public static class TagsBean {
        /**
         * count : 2942
         * name : С����
         * title : С����
         */

        private int count;
        private String name;
        private String title;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    @Override
    public String toString() {
        return "BooksBean{" +
                "rating=" + rating +
                ", subtitle='" + subtitle + '\'' +
                ", pubdate='" + pubdate + '\'' +
                ", origin_title='" + origin_title + '\'' +
                ", image='" + image + '\'' +
                ", binding='" + binding + '\'' +
                ", catalog='" + catalog + '\'' +
                ", pages='" + pages + '\'' +
                ", images=" + images +
                ", alt='" + alt + '\'' +
                ", id='" + id + '\'' +
                ", publisher='" + publisher + '\'' +
                ", isbn10='" + isbn10 + '\'' +
                ", isbn13='" + isbn13 + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", alt_title='" + alt_title + '\'' +
                ", author_intro='" + author_intro + '\'' +
                ", summary='" + summary + '\'' +
                //  ", series=" + series +
                ", price='" + price + '\'' +
                ", author=" + author +
                ", tags=" + tags +
                ", translator=" + translator +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.rating, flags);
        dest.writeString(this.subtitle);
        dest.writeString(this.pubdate);
        dest.writeString(this.origin_title);
        dest.writeString(this.image);
        dest.writeString(this.binding);
        dest.writeString(this.catalog);
        dest.writeString(this.pages);
        dest.writeParcelable(this.images, flags);
        dest.writeString(this.alt);
        dest.writeString(this.id);
        dest.writeString(this.publisher);
        dest.writeString(this.isbn10);
        dest.writeString(this.isbn13);
        dest.writeString(this.title);
        dest.writeString(this.url);
        dest.writeString(this.alt_title);
        dest.writeString(this.author_intro);
        dest.writeString(this.summary);
        dest.writeString(this.price);
        dest.writeStringList(this.author);
        dest.writeList(this.tags);
        dest.writeStringList(this.translator);
    }

    public BookBean() {
    }

    //for data test
    public BookBean(String title, String author, int state, String summary,String time) {
        this.setTitle(title);
        ArrayList<String> authors = new ArrayList<String>();
        authors.add(author);
        this.setAuthor(authors);
        UserNote userNote = new UserNote();
        userNote.setState(state);
        userNote.setNoteTime(time);
        this.setUserNote(userNote);
    }

    protected BookBean(Parcel in) {
        this.rating = in.readParcelable(RatingBean.class.getClassLoader());
        this.subtitle = in.readString();
        this.pubdate = in.readString();
        this.origin_title = in.readString();
        this.image = in.readString();
        this.binding = in.readString();
        this.catalog = in.readString();
        this.pages = in.readString();
        this.images = in.readParcelable(ImagesBean.class.getClassLoader());
        this.alt = in.readString();
        this.id = in.readString();
        this.publisher = in.readString();
        this.isbn10 = in.readString();
        this.isbn13 = in.readString();
        this.title = in.readString();
        this.url = in.readString();
        this.alt_title = in.readString();
        this.author_intro = in.readString();
        this.summary = in.readString();
        this.price = in.readString();
        this.author = in.createStringArrayList();
        this.tags = new ArrayList<TagsBean>();
        in.readList(this.tags, TagsBean.class.getClassLoader());
        this.translator = in.createStringArrayList();
    }

    public static final Creator<BookBean> CREATOR = new Creator<BookBean>() {
        @Override
        public BookBean createFromParcel(Parcel source) {
            return new BookBean(source);
        }

        @Override
        public BookBean[] newArray(int size) {
            return new BookBean[size];
        }
    };
}
