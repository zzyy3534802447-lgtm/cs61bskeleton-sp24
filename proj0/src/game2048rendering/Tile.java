package game2048rendering;

/** Represents the image of a numbered tile on a 2048 board.
 *  @author P. N. Hilfinger.
 */
public class Tile {

    /** A new tile with VALUE as its value at (x, y).  This
     *  constructor is private, so all tiles are created by the
     *  factory method create. */
    /** 创建一个新的图块，其值为 VALUE，坐标为 (x, y)。

     此构造函数是私有的，因此所有图块均由

     工厂方法 create 创建。 */
    private Tile(int value, int x, int y) {
        this._value = value;
        this._x = x;
        this._y = y;
        this._next = null;
        this._merged  = false;
    }

    /** Return whether this tile was already merged. */
    /** 返回此图块是否已被合并。 */
    public boolean wasMerged() {
        return _merged;
    }

    void setMerged(boolean merged) {
        this._merged = merged;
    }

    /** Return my current y-coordinate. */
    /** 返回我当前的 y 坐标。 */
    int y() {
        return _y;
    }

    /** Return my current x-coordinate. */
    int x() {
        return _x;
    }

    /** Return the value supplied to my constructor. */
    public int value() {
        return _value;
    }

    /** Return my next state.  Before I am moved or merged, I am my
     *  own successor. */
    /** 返回我的下一个状态。在我被移动或合并之前，我是我自己的

     * 继承者。*/
    Tile next() {
        return _next == null ? this : _next;
    }

    /** Set my next state when I am moved or merged. */
    /** 设置我被移动或合并后的下一个状态。 */
    void setNext(Tile otherTile) {
        _next = otherTile;
    }

    /** Return a new tile at (x, y) with value VALUE. */
    /** 返回位于 (x, y) 处、值为 VALUE 的新图块。 */
    public static Tile create(int value, int x, int y) {
        return new Tile(value, x, y);
    }

    /** Return the distance in rows or columns between me and my successor
     *  tile (0 if I have no successor). */
    /** 返回我与我的后继节点之间的行或列距离

     * tile（如果我没有后继节点，则返回 0）。*/
    int distToNext() {
        if (_next == null) {
            return 0;
        } else {
            return Math.max(Math.abs(_y - _next.y()),
                            Math.abs(_x - _next.x()));
        }
    }

    @Override
    public String toString() {
        return String.format("Tile %d at position (%d, %d)", value(), x(), y());
    }

    /** My value. */
    private final int _value;

    /** My last position on the board. */
    private final int _x;
    private final int _y;

    /** Whether I have merged. */
    private boolean _merged;

    /** Successor tile: one I am moved to or merged with. */
    private Tile _next;
}
