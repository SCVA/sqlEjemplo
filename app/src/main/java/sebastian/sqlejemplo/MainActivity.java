package sebastian.sqlejemplo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import sebastian.sqlejemplo.data.AgendaDBHelper;
import sebastian.sqlejemplo.data.Usuario;

public class MainActivity extends AppCompatActivity {

    AgendaDBHelper dbAyudante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        Button btnGuardar = (Button) findViewById( R.id.btnGuardar );
        EditText nombreTxt = (EditText) findViewById( R.id.usrText );
        EditText passwordTxt = (EditText) findViewById( R.id.passwordText );

        dbAyudante = new AgendaDBHelper(this);

        btnGuardar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario usuarioNuevo = new Usuario( nombreTxt.getText().toString(),Integer.parseInt(passwordTxt.getText().toString()) );
                dbAyudante.guardarUsuario( usuarioNuevo );
                Cursor cursorPersonaAlmacenada = dbAyudante.getUsuarioById("2");
                cursorPersonaAlmacenada.moveToFirst();
                Usuario usuarioAlmacenado = new Usuario(cursorPersonaAlmacenada);
                Toast.makeText(v.getContext(),usuarioAlmacenado.getName(), Toast.LENGTH_LONG).show();
            }
        } );
    }

}