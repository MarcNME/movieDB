package moviedb.renderer;

import javax.swing.table.DefaultTableCellRenderer;
import java.text.DateFormat;
import java.text.Format;

/*
 *	Use a formatter to format the cell Object
 */
public class FormatRenderer extends DefaultTableCellRenderer {
    private final Format formatter;

    /*
     *   Use the specified formatter to format the Object
     */
    public FormatRenderer(Format formatter) {
        this.formatter = formatter;
    }

    /*
     *  Use the default date/time formatter for the default locale
     */
    public static FormatRenderer getDateTimeRenderer() {
        return new FormatRenderer(DateFormat.getDateTimeInstance());
    }

    /*
     *  Use the default time formatter for the default locale
     */
    public static FormatRenderer getTimeRenderer() {
        return new FormatRenderer(DateFormat.getTimeInstance());
    }

    @Override
    public void setValue(Object value) {
        //  Format the Object before setting its value in the renderer

        try {
            if (value != null)
                value = formatter.format(value);
        } catch (IllegalArgumentException e) {
        }

        super.setValue(value);
    }
}
