package sebastian.sqlejemplo;

import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import sebastian.sqlejemplo.data.Usuario;

public class UsuarioRecyclerAdaptador extends RecyclerView.Adapter<UsuarioRecyclerAdaptador.ViewHolder>{

    private Cursor cursorListaUsuarios;
    private OnItemClickListener listenerClick;

    public UsuarioRecyclerAdaptador(OnItemClickListener listenerClick) {
        this.listenerClick = listenerClick;
    }

    interface OnItemClickListener{
        public void onClick(ViewHolder view, Usuario usuarioactualizado);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from( parent.getContext() ).inflate( R.layout.list_item_usuarios,parent,false );
        return new ViewHolder( v );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        cursorListaUsuarios.moveToPosition(position);
        Usuario usuarioSeleccionado = new Usuario( cursorListaUsuarios );
        holder.nameList.setText( usuarioSeleccionado.getName() );
        holder.passwordList.setText( usuarioSeleccionado.getPassword().toString() );
    }

    @Override
    public int getItemCount() {
        if (cursorListaUsuarios!=null)
            return cursorListaUsuarios.getCount();
        return 0;
    }

    public void swapCursor(Cursor nuevoCursor){
        if(nuevoCursor!=null){
            cursorListaUsuarios = nuevoCursor;
            notifyDataSetChanged();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView nameList;
        TextView passwordList;

        public ViewHolder(View itemView) {
            super( itemView );
            nameList = (TextView) itemView.findViewById( R.id.listNamePassword );
            passwordList = (TextView) itemView.findViewById( R.id.listPasswordUser );
            itemView.setOnClickListener( this );
        }

        @Override
        public void onClick(View v) {
            Usuario usarioActualizado = obtenerUsuario( getAdapterPosition() );
            usarioActualizado.setPassword( 0000 );
            listenerClick.onClick( this,usarioActualizado );
        }
    }

    private Usuario obtenerUsuario(int posicion){
        if (cursorListaUsuarios!=null){
            cursorListaUsuarios.moveToPosition( posicion );
            Usuario nuevoUsuario = new Usuario( cursorListaUsuarios );
            return nuevoUsuario;
        }
        return null;
    }

}
