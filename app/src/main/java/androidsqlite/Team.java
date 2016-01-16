package androidsqlite;

/**
 * Created by Emil on 16/01/16.
 */
public class Team {
    int _id;
    String _name;
    int _win_amount;
    int _lose_amount;
    int _goals;
    int _points;

    public Team() {}

    // Defining constructor to create new Instance of Team class
    public Team(int _id, String _name, int _win_amount, int _lose_amount, int _goals, int _points) {
        this._id = _id;
        this._name = _name;
        this._win_amount = _win_amount;
        this._lose_amount = _lose_amount;
        this._goals = _goals;
        this._points = _points;
    }

    public Team(String _name, int _win_amount, int _lose_amount, int _goals, int _points) {
        this._name = _name;
        this._win_amount = _win_amount;
        this._lose_amount = _lose_amount;
        this._goals = _goals;
        this._points = _points;
    }

    // Getters and Setter for defining and getting certain parameters
    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public int get_win_amount() {
        return _win_amount;
    }

    public void set_win_amount(int _win_amount) {
        this._win_amount = _win_amount;
    }

    public int get_lose_amount() {
        return _lose_amount;
    }

    public void set_lose_amount(int _lose_amount) {
        this._lose_amount = _lose_amount;
    }

    public int get_goals() {
        return _goals;
    }

    public void set_goals(int _goals) {
        this._goals = _goals;
    }

    public int get_points() {
        return _points;
    }

    public void set_points(int _points) {
        this._points = _points;
    }
}
