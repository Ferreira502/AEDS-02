import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFile
{
    public static void main ( String [ ] args )
    {
        ArrayList < String > list = new ArrayList < > ( );

        try
        {
            File file = new File ( "C:/git/xub/subjects/languages/java/read_file/words.txt" );
            Scanner scanner = new Scanner ( file );

            while ( scanner.hasNextLine ( ) )
            {
                String line = scanner.nextLine ( ).toLowerCase ( );
                StringBuilder assistant = new StringBuilder ( );

                for ( int i = 0; i < line.length ( ); i ++ )
                {
                    char c = line.charAt ( i );

                    if ( Character.isLetter ( c ) )
                    {
                        assistant.append ( c );
                    } else
                        {
                            if ( assistant.length ( ) > 0 )
                            {
                                list.add ( assistant.toString ( ) );
                                assistant.setLength ( 0 );
                            }
                        }
                }
            }

            scanner.close ( );
        } catch ( FileNotFoundException e )
            {
                System.out.println ( e.getMessage ( ) );
            }

        for ( String i : list )
        {
            System.out.println ( i );
        }
    }
}