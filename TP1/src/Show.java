import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;

/**
 * This class represents a movie/tv show from Netflix.
 */
public class Show {
    private int show_id;
    private String type;
    private String title;
    private String[] director;
    private String[] cast;
    private String[] country;
    private LocalDate date_added;
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
        this.director = new String[50];
        this.cast = new String[50];
        this.country = new String[50];
        this.date_added = LocalDate.now();
        this.release_year = 0;
        this.rating = "";
        this.duration = "";
        this.listed_in = new String[50];
        this.description = "";
        this.commaIndex = 0;
    }

    Show(int show_id, String type, String title, String[] director, String[] cast, String[] country,
            LocalDate date_added,
            int release_year, String rating, String duration, String[] listed_in, String description) {
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

    public void setCountry(String[] country) {
        this.country = country;
    }

    public void setDate_added(LocalDate date_added) {
        this.date_added = date_added;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDirector(String[] director) {
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

    public String[] getCountry() {
        return country;
    }

    public LocalDate getDate_added() {
        return date_added;
    }

    public String getDescription() {
        return description;
    }

    public String[] getDirector() {
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
                commasPositions[i] = line.indexOf(',', commasPositions[i - 1] + 1); // start search from last found
                                                                                    // comma
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
        String title = "";
        int startPoint = commasPositions[getCommaIndex()] + 1;
        int endPoint = commasPositions[getCommaIndex() + 1];
        int commasCount = 0;

        if (line.charAt(startPoint) == '"') {
            for (int i = startPoint; i < line.length(); i++) {
                if (line.charAt(i) != '"') {
                    title += line.charAt(i);
                    commasCount += (line.charAt(i) == ',') ? 1 : 0;
                } else if (i != startPoint && line.charAt(i) == '"') {
                    i = line.length();
                }

            }
        } else {
            int i = startPoint;
            while (i < endPoint) {
                title += line.charAt(i);
                i++;
            }
        }

        if (title.equals("")) {
            title = "-";
        }

        setCommaIndex(getCommaIndex() + 1 + commasCount);

        return title;
    }

    public String[] getDirectorFromLine(String line, int[] commasPositions) {
        String directorsString = "";
        int startPoint = commasPositions[this.getCommaIndex()] + 1;
        int endPoint = commasPositions[this.getCommaIndex() + 1];
        int commasCount = 0;

        if (line.charAt(startPoint) == '"') {
            for (int i = startPoint; i < line.length(); i++) {
                if (line.charAt(i) != '"') {
                    directorsString += line.charAt(i);
                    commasCount += (line.charAt(i) == ',') ? 1 : 0;
                } else if (i != startPoint && line.charAt(i) == '"') {
                    i = line.length();
                }
            }
        } else {
            int j = startPoint;

            while (j < endPoint) {
                directorsString += line.charAt(j);
                j++;
            }
        }

        setCommaIndex(getCommaIndex() + 1 + commasCount);

        String[] directors = directorsString.split(",");

        return directors;
    }

    public String[] getShowCastFromLine(String line, int[] commasPositions) {
        String castString = "";
        int startPoint = commasPositions[this.getCommaIndex()] + 1;
        int endPoint = commasPositions[this.getCommaIndex() + 1];
        int commasCount = 0;

        if (line.charAt(startPoint) == '"') {
            for (int i = startPoint; i < line.length(); i++) {
                if (line.charAt(i) != '"') {
                    castString += line.charAt(i);
                    commasCount += (line.charAt(i) == ',') ? 1 : 0;
                } else if (line.charAt(i) == '"' && line.charAt(i + 1) == '"'
                        || line.charAt(i) == '"' && line.charAt(i - 1) == '"') {
                    castString += line.charAt(i);
                    commasCount += (line.charAt(i) == ',') ? 1 : 0;
                } else if (i != startPoint && line.charAt(i) == '"') {
                    i = line.length();
                }
            }
        } else {
            int j = startPoint;

            while (j < endPoint) {
                castString += line.charAt(j);
                j++;
            }
        }

        setCommaIndex(getCommaIndex() + 1 + commasCount);

        String[] cast = castString.split(",");

        return cast;
    }

    public String[] getCountryFromLine(String line, int[] commasPositions) {
        String coutryString = "";
        int startPoint = commasPositions[this.getCommaIndex()] + 1;
        int endPoint = commasPositions[this.getCommaIndex() + 1];
        int commasCount = 0;

        if (line.charAt(startPoint) == '"') {
            for (int i = startPoint; i < line.length(); i++) {
                if (line.charAt(i) != '"') {
                    coutryString += line.charAt(i);
                    commasCount += (line.charAt(i) == ',') ? 1 : 0;
                } else if (i != startPoint && line.charAt(i) == '"') {
                    i = line.length();
                }
            }
        } else {
            int j = startPoint;

            while (j < endPoint) {
                coutryString += line.charAt(j);
                j++;
            }
        }

        setCommaIndex(getCommaIndex() + 1 + commasCount);

        String[] country = coutryString.split(",");

        return country;
    }

    public LocalDate getDateAddedFromLine(String line, int[] commasPositions) throws ParseException {
        String dateAddedString = "";
        int startPoint = commasPositions[getCommaIndex()] + 1;
        int commasCount = 0;

        if (line.charAt(startPoint) == '"') {
            for (int i = startPoint; i < line.length(); i++) {
                if (line.charAt(i) != '"') {
                    dateAddedString += line.charAt(i);
                    commasCount += (line.charAt(i) == ',') ? 1 : 0;
                } else if (i != startPoint && line.charAt(i) == '"') {
                    i = line.length();
                }

            }
        } else {
            dateAddedString = "-";
        }

        setCommaIndex(getCommaIndex() + 1 + commasCount);

        if (!dateAddedString.equals("-")) {
            dateAddedString = dateAddedString.trim();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.US);
            LocalDate date_added = LocalDate.parse(dateAddedString, formatter);
            return date_added;
        } else {
            // Movies/TV Shows without date are going to have its date_added attribute set
            // to 1969-01-01
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.US);
            LocalDate date_added = LocalDate.parse("January 01, 1969", formatter);
            return date_added;
        }
    }

    public int getReleaseYearFromLine(String line, int[] commasPositions) {
        int startPoint = commasPositions[getCommaIndex()] + 1;
        int endPoint = commasPositions[getCommaIndex() + 1];
        int i = startPoint;
        String releaseYearString = "";

        while (i < endPoint) {
            releaseYearString += line.charAt(i);
            i++;
        }

        int release_year = (releaseYearString.equals("")) ? 0 : Integer.parseInt(releaseYearString);

        setCommaIndex(getCommaIndex() + 1);

        return release_year;
    }

    public String getRatingFromLine(String line, int[] commasPositions) {
        int i = commasPositions[getCommaIndex()] + 1;
        String rating = "";

        while (i < commasPositions[getCommaIndex() + 1]) {
            rating += line.charAt(i);
            i++;
        }

        if (rating.equals("")) {
            rating = "-";
        }

        setCommaIndex(getCommaIndex() + 1);

        return rating;
    }

    public String getDurationFromLine(String line, int[] commasPositions) {
        int i = commasPositions[getCommaIndex()] + 1;
        String duration = "";

        while (i < commasPositions[getCommaIndex() + 1]) {
            duration += line.charAt(i);
            i++;
        }

        if (duration.equals("")) {
            duration = "-";
        }

        setCommaIndex(getCommaIndex() + 1);

        return duration;
    }

    public String[] getListedInFromLine(String line, int[] commasPositions) {
        String listString = "";
        int startPoint = commasPositions[this.getCommaIndex()] + 1;
        int endPoint = commasPositions[this.getCommaIndex() + 1];
        int commasCount = 0;

        if (line.charAt(startPoint) == '"') {
            for (int i = startPoint; i < line.length(); i++) {
                if (line.charAt(i) != '"') {
                    listString += line.charAt(i);
                    commasCount += (line.charAt(i) == ',') ? 1 : 0;
                } else if (i != startPoint && line.charAt(i) == '"') {
                    i = line.length();
                }
            }
        } else {
            int j = startPoint;

            while (j < endPoint) {
                listString += line.charAt(j);
                j++;
            }
        }

        setCommaIndex(getCommaIndex() + 1 + commasCount);

        String[] listed_in = listString.split(",");

        return listed_in;
    }

    public String getDescriptionFromLine(String line, int[] commasPositions) {
        int i = commasPositions[getCommaIndex()] + 1;
        String description = "";

        while (i < commasPositions.length) {
            description += line.charAt(i);
            i++;
        }

        if (description.equals("")) {
            description = "-";
        }

        System.out.println(description);

        return description;
    }

    public void getShowsInformations(String line) throws ParseException {
        int[] commasPositions = getCommas(line);

        setShow_id(getShowIdFromLine(line));
        setType(getShowTypeFromLine(line, commasPositions));
        setTitle(getShowTitleFromLine(line, commasPositions));
        setDirector(getDirectorFromLine(line, commasPositions));
        setCast(getShowCastFromLine(line, commasPositions));
        setCountry(getCountryFromLine(line, commasPositions));
        setDate_added(getDateAddedFromLine(line, commasPositions));
        setRelease_year(getReleaseYearFromLine(line, commasPositions));
        setRating(getRatingFromLine(line, commasPositions));
        setDuration(getDurationFromLine(line, commasPositions));
        setListed_in(getListedInFromLine(line, commasPositions));
        setDescription(getDescriptionFromLine(line, commasPositions));
    }

}
