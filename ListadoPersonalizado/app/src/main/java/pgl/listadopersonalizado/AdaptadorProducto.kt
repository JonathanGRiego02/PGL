import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import pgl.listadopersonalizado.Producto
import pgl.listadopersonalizado.R


class AdaptadorProducto(context: Context, private val productList: List<Producto>) :
    ArrayAdapter<Producto>(context, 0, productList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val product = getItem(position)
        var view = convertView

        // Si la vista no está reutilizada, infla la vista personalizada del producto
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.lista_items, parent, false)
        }

        // Referencias a los TextView en el layout personalizado
        val productTextView = view?.findViewById<TextView>(R.id.productTextView)

        // Formato del producto: Producto (Cantidad) = Precio €
        val formattedText = if (product?.cantidad?.isNotEmpty() == true && product.precio?.isNotEmpty() == true) {
            "${product.nombre} (${product.cantidad}) = ${product.precio} €"
        } else if (product?.cantidad?.isNotEmpty() == true) {
            "${product.nombre} (${product.cantidad})"
        } else if (product?.precio?.isNotEmpty() == true) {
            "${product.nombre} = ${product.precio} €"
        } else {
            product?.nombre ?: ""
        }

        // Establecer el texto con el formato correspondiente
        productTextView?.text = formattedText

        return view!!
    }
}