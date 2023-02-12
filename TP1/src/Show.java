import java.util.Date;

public class Show {
    private int show_id;
    private String type;
    private String title;
    private String director;
    private String[] cast;
    private String country;
    private Date date_added;
    private int release_year;
    private String rating;
    private String duration;
    private String[] listed_in;
    private String description;
    private int commaIndex;

    Show() {
        this.show_id = 0;
        this.type = "";
        this.title = "";
        this.director = "";
        this.cast = new String[50];
        this.country = "";
        this.date_added = new Date();
        this.release_year = 0;
        this.rating = "";
        this.duration = "";
        this.listed_in = new String[50];
        this.description = "";
        this.commaIndex = 0;
    }

    Show(int show_id, String type, String title, String director, String[] cast, String country, Date date_added, int release_year, String rating, String duration, String[] listed_in, String description) {
        this.show_id = show_id;
        this.type = type;
        this.title = title;
        this.director = director;
        this.cast = cast;
        this.country = country;
        this.date_added = date_added;
        this.release_year = release_year;
        this.rating = rating;
        this.duration = duration;
        this.listed_in = listed_in;
        this.description = description;
    }

    public void setCast(String[] cast) {
        this.cast = cast;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setDate_added(Date date_added) {
        this.date_added = date_added;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setListed_in(String[] listed_in) {
        this.listed_in = listed_in;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setRelease_year(int release_year) {
        this.release_year = release_year;
    }

    public void setShow_id(int show_id) {
        this.show_id = show_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCommaIndex(int commaIndex) {
        this.commaIndex = commaIndex;
    }

    public String[] getCast() {
        return cast;
    }

    public String getCountry() {
        return country;
    }

    public Date getDate_added() {
        return date_added;
    }

    public String getDescription() {
        return description;
    }

    public String getDirector() {
        return director;
    }

    public String getDuration() {
        return duration;
    }

    public String[] getListed_in() {
        return listed_in;
    }

    public String getRating() {
        return rating;
    }

    public int getRelease_year() {
        return release_year;
    }

    public int getShow_id() {
        return show_id;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public int getCommaIndex() {
        return commaIndex;
    }

    public int[] getCommas(String line) {
        int[] commasPositions = new int[line.length()];

        for (int i = 0; i < line.length(); i++) {
            if (i == 0) {
                commasPositions[i] = line.indexOf(',');
            } else {
                commasPositions[i] = line.indexOf(',', commasPositions[i - 1] + 1); // start search from last found comma
            }
        }

        return commasPositions;
    }

    public int getShowIdFromLine(String line) {
        int show_id = 0;
        String tmp = "";

        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) != ',') {
                tmp += line.charAt(i);
            } else {
                i = line.length();
            }
        }

        tmp = tmp.replace('s', ' ').trim();

        show_id = Integer.parseInt(tmp);

        return show_id;
    }

    public String getShowTypeFromLine(String line, int[] commasPositions) {
        int i = commasPositions[getCommaIndex()] + 1;
        String type = "";

        while (i < commasPositions[getCommaIndex() + 1]) {
            type += line.charAt(i);
            i++;
        }

        if (type.equals("")) {
            type = "-";
        }

        setCommaIndex(getCommaIndex() + 1);

        return type;
    }

    public String getShowTitleFromLine(String line, int[] commasPositions) {
        int i = commasPositions[getCommaIndex()] + 1;
        String title = "";

        while (i < commasPositions[getCommaIndex() + 1]) {
            title += line.charAt(i);
            i++;
        }

        if (title.equals("")) {
            title = "-";
        }

        setCommaIndex(getCommaIndex() + 1);

        return title;
    }

    public void getShowsInformations(String line) {
        int[] commasPositions = getCommas(line);

        setShow_id(getShowIdFromLine(line));
        setType(getShowTypeFromLine(line, commasPositions));
        setTitle(getShowTitleFromLine(line, commasPositions));
    }

}
