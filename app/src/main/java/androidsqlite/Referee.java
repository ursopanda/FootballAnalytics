package androidsqlite;

/**
 * Created by esyundyukov on 16/01/16.
 */
public class Referee {
    int _id;
    String _name;
    String _surname;
    int _cards;
    int _games;

    public Referee() {}

    public Referee(int _id, String _name, String _surname, int _cards, int _games) {
        this._id = _id;
        this._name = _name;
        this._surname = _surname;
        this._cards = _cards;
        this._games = _games;
    }

    public Referee(String _name, String _surname, int _cards, int _games) {
        this._name = _name;
        this._surname = _surname;
        this._cards = _cards;
        this._games = _games;
    }

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

    public String get_surname() {
        return _surname;
    }

    public void set_surname(String _surname) {
        this._surname = _surname;
    }

    public int get_cards() {
        return _cards;
    }

    public void set_cards(int _cards) {
        this._cards = _cards;
    }

    public int get_games() {
        return _games;
    }

    public void set_games(int _games) {
        this._games = _games;
    }
}
