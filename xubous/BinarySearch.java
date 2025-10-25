// package subjects.aeds.lab.verde.tp05;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

class Game
{
    public int id;
    public String name;
    public String releaseDate;
    public int estimatedOwners;
    public double price;
    public String [ ] supportedLanguages;
    public int metacriticScore;
    public double userScore;
    public int achievements;
    public String [ ] publishers;
    public String [ ] developers;
    public String [ ] categories;
    public String [ ] genres;
    public String [ ] tags;
    public String rawEstimatedOwners;
    public String rawPrice;
    public String rawUserScore;
    public String rawAchievements;

    public Game ( )
    {
        this.id = 0;
        this.name = "";
        this.releaseDate = "dd/mm/aaaa";
        this.estimatedOwners = 0;
        this.price = 0.0;
        this.supportedLanguages = null;
        this.metacriticScore = - 1;
        this.userScore = - 1.0;
        this.achievements = 0;
        this.publishers = null;
        this.developers = null;
        this.categories = null;
        this.genres = null;
        this.tags = null;
        this.rawEstimatedOwners = "";
        this.rawPrice = "";
        this.rawUserScore = "";
        this.rawAchievements = "";
    }

    public Game ( int id, String name, String releaseDate, String rawEstimatedOwners, String rawPrice, String [ ] supportedLanguages, int metacriticScore, String rawUserScore, String rawAchievements, String [ ] publishers, String [ ] developers, String [ ] categories, String [ ] genres, String [ ] tags )
    {
        this.id = id;
        this.name = name.trim ( );
        this.releaseDate = releaseDate;
        this.rawEstimatedOwners = rawEstimatedOwners;
        this.rawPrice = rawPrice;
        this.supportedLanguages = supportedLanguages;
        this.metacriticScore = metacriticScore;
        this.rawUserScore = rawUserScore;
        this.rawAchievements = rawAchievements;
        this.publishers = publishers;
        this.developers = developers;
        this.categories = categories;
        this.genres = genres;
        this.tags = tags;
        this.estimatedOwners = 0;
        this.price = 0.0;
        this.userScore = - 1.0;
        this.achievements = 0;
    }

    public Game processBuilder ( Game game )
    {
        try
        {
            String input = game.releaseDate.trim ( );
            Date date = null;

            String [ ] formatos = {
                "MMM dd, yyyy",    
                "MMMM dd, yyyy",   
                "yyyy-MM-dd",      
                "dd/MM/yyyy",      
                "MM/dd/yyyy"       
            };

            for ( String f : formatos )
            {
                try
                {
                    date = new SimpleDateFormat ( f, Locale.ENGLISH ).parse ( input );
                    break;
                } catch ( ParseException ignored ) { }
            }

            if ( date != null )
            {
                game.releaseDate = new SimpleDateFormat ( "dd/MM/yyyy" ).format ( date );
            } else    
                {
                    game.releaseDate = "01/01/1970";
                }

        } catch ( Exception e )
            {
                game.releaseDate = "01/01/1970";
            }

        StringBuilder sbo = new StringBuilder ( );

        game.rawEstimatedOwners = game.rawEstimatedOwners.trim ( );

        for ( int i = 0; i < game.rawEstimatedOwners.length ( ); i ++ )
        {
            if ( Character.isDigit ( game.rawEstimatedOwners.charAt ( i ) ) )
            {
                sbo.append ( game.rawEstimatedOwners.charAt ( i ) );
            }
        }

        if ( sbo.length ( ) > 0 )
        {
            game.estimatedOwners = Integer.parseInt ( sbo.toString ( ) );
        } else
            {
                game.estimatedOwners = 0;
            }

        if ( game.rawPrice.equalsIgnoreCase ( "Free to Play" ) )
        {
            game.price = 0.0;
        } else
            {
                try
                {
                    game.price = Double.parseDouble ( game.rawPrice );
                } catch ( Exception e )
                    {
                        game.price = 0.0;
                    }
        }

        if ( game.metacriticScore == 0 )
        {
            game.metacriticScore = - 1;
        }

        if ( game.rawUserScore.equalsIgnoreCase ( "tbd" ) )
        {
            game.userScore = - 1.0;
        } else
            {
                try
                {
                    game.userScore = Double.parseDouble ( game.rawUserScore );
                } catch ( Exception e )
                    {
                        game.userScore = - 1.0;
                    }
        }

        if ( game.rawAchievements.trim ( ).equals ( "" ) )
        {
            game.achievements = 0;
        } else
            {
                game.achievements = Integer.parseInt ( game.rawAchievements );
            }

        return game;
    }

    public static void printGames ( int index, int id, ArrayList < Game > games )
    {
        if ( index >= games.size ( ) )
        {
            return ;
        }

        if ( games.get ( index ).id == id )
        {
            System.out.println ( games.get ( index ).toString ( ) );
            
            return ;
        }

        printGames ( index + 1, id, games );
    }

    public static String toArrayString ( String [ ] array )
    {
        if ( array == null ) return "[]";

        ArrayList < String > clean = new ArrayList < String > ( );
        for ( String s : array )
        {
            if ( s != null && ! s.trim ( ).isEmpty ( ) )
            {
                clean.add ( s.trim ( ) );
            }
        }

        return "[" + String.join ( ", ", clean ) + "]";
    }

    @Override
    public String toString ( )
    {
        return "=> " + this.id + " ## " +
            this.name + " ## " +
            this.releaseDate + " ## " +
            this.estimatedOwners + " ## " +
            this.price + " ## " +
            toArrayString ( this.supportedLanguages ) + " ## " +
            this.metacriticScore + " ## " +
            this.userScore + " ## " +
            this.achievements + " ## " +
            toArrayString ( this.developers ) + " ## " +
            toArrayString ( this.publishers ) + " ## " +
            toArrayString ( this.categories ) + " ## " +
            toArrayString ( this.genres ) + " ## " +
            toArrayString ( this.tags ) + " ##";
    }

}

public class BinarySearch 
{
    static void merge ( ArrayList < Game > games, int left, int mid, int right )
    {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        ArrayList < Game > L = new ArrayList < Game > ( );
        ArrayList < Game > R = new ArrayList < Game > ( );

        for ( int i = 0; i < n1; i ++ ) L.add ( games.get ( left + i ) );
        for ( int j = 0; j < n2; j ++ ) R.add ( games.get ( mid + 1 + j ) );

        int i = 0, j = 0, k = left;

        while ( i < n1 && j < n2 )
        {
            if ( L.get ( i ).name.compareToIgnoreCase ( R.get ( j ).name ) <= 0 )
            {
                games.set ( k, L.get ( i ) );
                i ++;
            } else
                {
                    games.set ( k, R.get ( j ) );
                    j ++;
                }
            k ++;
        }

        while ( i < n1 )
        {
            games.set ( k, L.get ( i ) );
            i ++;
            k ++;
        }

        while ( j < n2 )
        {
            games.set ( k, R.get ( j ) );
            j ++;
            k ++;
        }
    }

    static void mergeSort ( ArrayList < Game > games, int left, int right )
    {
        if ( left < right )
        {
            int mid = ( left + right ) / 2;
            mergeSort ( games, left, mid );
            mergeSort ( games, mid + 1, right );
            merge ( games, left, mid, right );
        }
    }

    static boolean sort ( ArrayList < Game > games )
    {
        if ( games == null || games.size ( ) == 0 ) return false;

        for ( Game g : games )
        {
            g.name = g.name.trim ( );
        }

        mergeSort ( games, 0, games.size ( ) - 1 );
        return true;
    }

    static boolean binarySearch ( ArrayList < Game > games, int init, int end, String key )
    {
        if ( init > end ) return false;

        int mid = ( init + end ) / 2;
        int cmpy = games.get ( mid ).name.compareToIgnoreCase ( key.trim ( ) );

        if ( cmpy == 0 )
        {
            return true;
        } else if ( cmpy < 0 )
            {
                return binarySearch ( games, mid + 1, end, key );
            } else
                {
                    return binarySearch ( games, init, mid - 1, key );
                }
    }

    public static void main ( String [ ] args )
    {
        String path = "/tmp/games.csv";
        ArrayList < Game > games = new ArrayList < Game > ( );

        try ( BufferedReader br = new BufferedReader ( new FileReader ( path ) ) )
        {
            br.readLine ( );
            String line;

            while ( ( line = br.readLine ( ) ) != null )
            {
                ArrayList < String > camposList = new ArrayList < String > ( );
                StringBuilder sb = new StringBuilder ( );
                boolean dentroAspas = false;

                for ( int i = 0; i < line.length ( ); i ++ )
                {
                    char c = line.charAt ( i );

                    if ( c == '"' )
                    {
                        dentroAspas = ! dentroAspas;
                    } else if ( c == ',' && ! dentroAspas )
                        {
                            camposList.add ( sb.toString ( ).trim ( ) );
                            sb.setLength ( 0 );
                        } else
                            {
                                sb.append ( c );
                            }
                }
                camposList.add ( sb.toString ( ).trim ( ) );

                String [ ] campos = camposList.toArray ( new String [ 0 ] );

                if ( campos.length < 14 )
                {
                    continue;
                }

                Game game = new Game (
                    Integer.parseInt ( campos [ 0 ] ),
                    campos [ 1 ],
                    campos [ 2 ],
                    campos [ 3 ],
                    campos [ 4 ],
                    campos [ 5 ].replaceAll ( "[\\[\\]\"\'']", "" ).split ( "," ),
                    Integer.parseInt ( campos [ 6 ] ),
                    campos [ 7 ],
                    campos [ 8 ],
                    campos [ 9 ].replaceAll ( "[\\[\\]\"\'']", "" ).split ( "," ),
                    campos [ 10 ].replaceAll ( "[\\[\\]\"\'']", "" ).split ( "," ),
                    campos [ 11 ].replaceAll ( "[\\[\\]\"\'']", "" ).split ( "," ),
                    campos [ 12 ].replaceAll ( "[\\[\\]\"\'']", "" ).split ( "," ),
                    campos [ 13 ].replaceAll ( "[\\[\\]\"\'']", "" ).split ( "," )
                );

                game.processBuilder ( game );
                games.add ( game );
            }

        } catch ( IOException e )
            {
                e.printStackTrace ( );
            }

        Scanner sc = new Scanner ( System.in );

        ArrayList < Integer > gamesIds = new ArrayList < Integer > ( );
        ArrayList < Game > selectedGames = new ArrayList < Game > ( );

        while ( true )
        {
            String input = sc.nextLine ( ).trim ( );

            if ( input.equals ( "FIM" ) )
            {
                break;
            }

            try
            {
                int id = Integer.parseInt ( input );
                gamesIds.add ( id );

            } catch ( Exception e )
                {
                }
        }

        for ( int id : gamesIds )
        {
            for ( Game g : games )
            {
                if ( g.id == id )
                {
                    selectedGames.add ( g );
                    break;
                }
            }
        }

        sort ( selectedGames );

        ArrayList < String > keys = new ArrayList < String > ( );

        while ( true )
        {
            String key = sc.nextLine ( ).trim ( );

            if ( key.equals ( "FIM" ) )
            {
                break;
            }

            keys.add ( key );
        }

        for ( String key : keys )
        {
            System.out.println ( binarySearch ( selectedGames, 0, selectedGames.size ( ) - 1, key ) ? " SIM" : " NAO" );
        }

        sc.close ( );
    }
}
