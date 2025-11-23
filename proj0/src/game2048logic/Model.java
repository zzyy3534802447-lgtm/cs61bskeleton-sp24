package game2048logic;

import game2048rendering.Board;
import game2048rendering.Side;
import game2048rendering.Tile;

import java.util.Formatter;


/** The state of a game of 2048.
 *  @author P. N. Hilfinger + Josh Hug
 */
public class Model {
    /** Current contents of the board. */
    private final Board board;
    /** Current score. */
    private int score;

    /* Coordinate System: column x, row y of the board (where x = 0,
     * y = 0 is the lower-left corner of the board) will correspond
     * to board.tile(x, y).  Be careful!
     * 坐标系：棋盘的 x 列，y 行（其中 x = 0，

     * y = 0 为棋盘的左下角）将对应于

     * board.tile(x, y)。请注意！

     */

    /** Largest piece value. */
    public static final int MAX_PIECE = 2048;

    /** A new 2048 game on a board of size SIZE with no pieces
     *  and score 0.
     *  /** 一个新的 2048 游戏，棋盘大小为 SIZE，没有棋子
     * * 且得分为 0。*/
    public Model(int size) {
        board = new Board(size);
        score = 0;
    }

    /** A new 2048 game where RAWVALUES contain the values of the tiles
     * (0 if null). VALUES is indexed by (x, y) with (0, 0) corresponding
     * to the bottom-left corner. Used for testing purposes. */
    /** 一个新的 2048 游戏，其中 RAWVALUES 包含图块的值

     *（如果为空则为 0）。VALUES 的索引为 (x, y)，其中 (0, 0) 对应于

     * 左下角。用于测试目的。*/
    public Model(int[][] rawValues, int score) {
        board = new Board(rawValues);
        this.score = score;
    }

    /** Return the current Tile at (x, y), where 0 <= x < size(),
     *  0 <= y < size(). Returns null if there is no tile there.
     *  Used for testing. */
    /** 返回当前位于 (x, y) 的图块，其中 0 <= x < size()，

     * 0 <= y < size()。如果该位置没有图块，则返回 null。

     * 用于测试。*/
    public Tile tile(int x, int y) {

        return board.tile(x, y);
    }

    /** Return the number of squares on one side of the board. */
    /** 返回棋盘一侧的方格数。 */
    public int size() {
        return board.size();
    }

    /** Return the current score. */
    public int score() {
        return score;
    }


    /** Clear the board to empty and reset the score. */
    /** 清空棋盘，重置分数。 */
    public void clear() {
        score = 0;
        board.clear();
    }

    /** Add TILE to the board. There must be no Tile currently at the
     *  same position. */
    /** 将图块添加到棋盘上。当前该位置不能有其他图块。

     * * 相同位置。 */
    public void addTile(Tile tile) {
        board.addTile(tile);
    }

    /** Return true iff the game is over (there are no moves, or
     *  there is a tile with value 2048 on the board). */
    /** 如果游戏结束（没有可走的棋步，或者

     * 棋盘上存在值为 2048 的方块），则返回 true。 */
    public boolean gameOver() {
        return maxTileExists() || !atLeastOneMoveExists();
    }

    /** Returns this Model's board. */
    public Board getBoard() {
        return board;
    }

    /** Returns true if at least one space on the Board is empty.
     *  Empty spaces are stored as null.
     * */
    /** 如果棋盘上至少有一个空格，则返回 true。

     * 空格存储为 null。

     * */
    public boolean emptySpaceExists() {
        // TODO: Task 2. Fill in this function.
        for(int x=0;x<size();x++){
            for(int y=0;y<size();y++){
                if(tile(x,y)==null) return true;
            }
        }
        return false;
    }

    /**
     * Returns true if any tile is equal to the maximum valid value.
     * Maximum valid value is given by this.MAX_PIECE. Note that
     * given a Tile object t, we get its value with t.value().
     */
    /**

     * 如果存在任何图块等于最大有效值，则返回 true。

     * 最大有效值由 this.MAX_PIECE 给出。注意：

     * 给定一个 Tile 对象 t，我们可以使用 t.value() 获取其值。

     */
    public boolean maxTileExists() {
        // TODO: Task 3. Fill in this function.
        for(int x=0;x<size();x++){
            for(int y=0;y<size();y++){
                Tile j=tile(x,y);
                if(j!=null && j.value()==this.MAX_PIECE) return true;
            }
        }
        return false;
    }

    /**
     * Returns true if there are any valid moves on the board.
     * There are two ways that there can be valid moves:
     * 1. There is at least one empty space on the board.
     * 2. There are two adjacent tiles with the same value.
     */
    /**

     * 如果棋盘上存在有效走法，则返回 true。

     * 存在有效走法有两种情况：

     * 1. 棋盘上至少有一个空格。

     * 2. 有两个相邻的方块值相同。

     */
    public boolean atLeastOneMoveExists() {
        // TODO: Fill in this function.
        for(int x=0;x<size();x++){
            for(int y=0;y<size();y++){
             if(tile(x,y)==null)return  true;
             int j=tile(x,y).value();
             if(x+1<size() && tile(x+1,y)!=null && j==tile(x+1,y).value())return true;
             if(x-1>=0 && tile(x-1,y)!=null && j==tile(x-1,y).value())return true;
             if(y+1<size() && tile(x,y+1)!=null && j==tile(x,y+1).value())return true;
             if(y-1>=0 && tile(x,y-1)!=null && j==tile(x,y-1).value())return true;
            }
        }

        return false;
    }

    /**
     * Moves the tile at position (x, y) as far up as possible.
     *
     * Rules for Tilt:
     * 1. If two Tiles are adjacent in the direction of motion and have
     *    the same value, they are merged into one Tile of twice the original
     *    value and that new value is added to the score instance variable
     * 2. A tile that is the result of a merge will not merge again on that
     *    tilt. So each move, every tile will only ever be part of at most one
     *    merge (perhaps zero).
     * 3. When three adjacent tiles in the direction of motion have the same
     *    value, then the leading two tiles in the direction of motion merge,
     *    and the trailing tile does not.
     */
    /**

     * 将位置 (x, y) 的图块向上移动至尽可能远。

     *

     * 倾斜规则：

     * 1. 如果两个图块在移动方向上相邻且

     * 值相同，则将它们合并为一个图块，其值是原图块的两倍。

     * 合并后的新图块值将添加到分数实例变量中。

     * 2. 合并后的图块在本次倾斜操作中不会再次合并。

     * 因此，每次移动，每个图块最多只会参与一次合并（也可能不参与）。

     * 3. 当三个相邻图块在移动方向上具有相同的值时，

     * 移动方向上最前面的两个图块合并，

     * 而最后面的图块不合并。

     */
    public void moveTileUpAsFarAsPossible(int x, int y) {
        Tile currTile = board.tile(x, y);
        int myValue = currTile.value();
        int targetY = y;
        int nextY=y+1;
        if(nextY==size())return;
        if(currTile==null)return;
        while (nextY<size()&&board.tile(x,nextY)==null){
            targetY=nextY;
            nextY++;
        }
        //if(board.tile(x,y)==null) return;
        //上面3格子全是空的
        if(nextY==size()){
            board.move(x,targetY,currTile);
        }
        else {
            //上面有非空的格子，两个一样
            if(board.tile(x,nextY)!=null && board.tile(x,nextY).value()==board.tile(x,y).value()&& !board.tile(x,nextY).wasMerged()) {
            board.move(x,nextY,currTile);
            myValue=currTile.value()*2;
               // if(myValue>score)
                    score+=myValue;
                return ;
                //两不一样
                 } else {if(targetY!=y)board.move(x,targetY,currTile);
                         return;
                   }

        }


        // TODO: Tasks 5, 6, and 10. Fill in this function.
    }

    /** Handles the movements of the tilt in column x of the board
     * by moving every tile in the column as far up as possible.
     * The viewing perspective has already been set,
     * so we are tilting the tiles in this column up.
     * */
    /** 处理棋盘第 x 列的倾斜移动

     * 将该列中的每个方块尽可能向上移动。

     * 视角已设置好，

     * 因此，我们将该列中的方块向上倾斜。

     * */
    public void tiltColumn(int x) {
        for(int i=size()-1;i>=0;i--){
            if(board.tile(x,i)!=null){
                moveTileUpAsFarAsPossible(x,i);

            }
        }return;
        // TODO: Task 7. Fill in this function.
    }

    public void tilt(Side side) {
    board.setViewingPerspective(side);

        for(int x=0;x<size();x++){
            tiltColumn(x);
        }
        board.setViewingPerspective(Side.NORTH);
        // TODO: Tasks 8 and 9. Fill in this function.
    }

    /** Tilts every column of the board toward SIDE.
     */
    /** 将棋盘的每一列都向侧面倾斜。

     */
    public void tiltWrapper(Side side) {
        board.resetMerged();
        tilt(side);
    }


    @Override
    public String toString() {
        Formatter out = new Formatter();
        out.format("%n[%n");
        for (int y = size() - 1; y >= 0; y -= 1) {
            for (int x = 0; x < size(); x += 1) {
                if (tile(x, y) == null) {
                    out.format("|    ");
                } else {
                    out.format("|%4d", tile(x, y).value());
                }
            }
            out.format("|%n");
        }
        String over = gameOver() ? "over" : "not over";
        out.format("] %d (game is %s) %n", score(), over);
        return out.toString();
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof Model m) && this.toString().equals(m.toString());
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}
