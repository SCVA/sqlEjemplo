package sebastian.sqlejemplo.data;

import android.provider.BaseColumns;

public class UsuarioContract {

    public static abstract class UsuarioEntry implements BaseColumns{
        public static final String TABLE_NAME ="usuario";
        public static final String NAME = "name";
        public static final String PASSWORD = "password";
    }

}
