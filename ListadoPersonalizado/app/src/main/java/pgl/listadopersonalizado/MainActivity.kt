package pgl.listadopersonalizado
import AdaptadorProducto
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var productAdapter: AdaptadorProducto
    private var productList = mutableListOf<Producto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar el adaptador personalizado
        productAdapter = AdaptadorProducto(this, productList)
        val listView = findViewById<ListView>(R.id.listView)
        listView.adapter = productAdapter

        // Registrar el menú contextual para eliminar productos
        registerForContextMenu(listView)

        // Referencias a los EditTexts y al botón
        val productNameInput = findViewById<EditText>(R.id.productName)
        val productQuantityInput = findViewById<EditText>(R.id.productQuantity)
        val productPriceInput = findViewById<EditText>(R.id.productPrice)
        val addProductButton = findViewById<Button>(R.id.addProductButton)

        // Configurar la acción del botón para agregar el producto
        addProductButton.setOnClickListener {
            val name = productNameInput.text.toString().trim()
            val quantity = productQuantityInput.text.toString().trim()
            val price = productPriceInput.text.toString().trim()

            if (name.isNotEmpty()) {
                // Agregar el producto a la lista
                addProduct(name, quantity, price)

                // Limpiar los campos después de agregar
                productNameInput.text.clear()
                productQuantityInput.text.clear()
                productPriceInput.text.clear()
            } else {
                Toast.makeText(this, "El nombre del producto es obligatorio", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun addProduct(name: String, quantity: String, price: String) {
        val newProduct = Producto(name, quantity, price)
        productList.add(newProduct)
        productAdapter.notifyDataSetChanged()
        updateTotals()
    }

    // Método para actualizar los totales
    private fun updateTotals() {
        val totalItems = productList.size
        val totalPrice = productList.sumOf { it.precio.toDoubleOrNull() ?: 0.0 }

        // Actualizar el TextView del header con el total de productos y precio
        val shoppingListHeader = findViewById<TextView>(R.id.shoppingListHeader)
        shoppingListHeader.text = "Lista de la compra: $totalItems productos = $totalPrice €"
    }

    // Crear el menú contextual
    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context_menu, menu)
        menu?.setHeaderTitle("Acciones")
    }

    // Manejar la selección del menú contextual
    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.delete_product -> {
                // Obtener la posición del producto en la lista
                val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
                val position = info.position

                // Eliminar el producto
                productList.removeAt(position)
                productAdapter.notifyDataSetChanged()

                // Actualizar los totales
                updateTotals()

                true
            }
            else -> super.onContextItemSelected(item)
        }
    }
}