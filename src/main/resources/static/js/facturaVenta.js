
    /*
    ============================================================
    ARRAY DE DETALLES
    ============================================================
    */

    let detalles = [];



    /*
    CARGAR PRECIO
    */

    function cargarPrecio(select) {


        const productoId = select.value;


        const precioInput =
            document.getElementById("precioProducto");


        if (!productoId) {

            precioInput.value = 0;

            actualizarTotalModal();

            return;

        }


        fetch("/productos/" + productoId + "/precio")


            .then(response => {


                if (!response.ok) {

                    throw new Error(
                        "No se pudo obtener el precio"
                    );

                }


                return response.json();

            })


            .then(data => {


                if (typeof data === "object") {

                    precioInput.value =
                        data.precio ?? 0;

                }

                else {

                    precioInput.value = data;

                }


                actualizarTotalModal();

            })


            .catch(error => {


                console.error(error);


                precioInput.value = 0;


                actualizarTotalModal();

            });

    }

    /*
============================================================
OBTENER RUC DEL CLIENTE
============================================================
*/

function obtenerDatosCliente(select) {

    const clienteId = select.value;

    if (!clienteId) {
        document.getElementById("rucCliente").value = "";
        document.getElementById("telefonoCliente").value = "";
        document.getElementById("direccionCliente").value = "";
        return;
    }

    fetch("/cliente/" + clienteId)

        .then(response => response.json())

        .then(cliente => {

            document.getElementById("rucCliente").value =
                cliente.ruc ?? "";

            document.getElementById("telefonoCliente").value =
                cliente.telefono ?? "";

            document.getElementById("direccionCliente").value =
                cliente.direccion ?? "";

        })

        .catch(error => {

            console.error(error);

        });
}




    /*
    ============================================================
    CALCULAR TOTAL DEL MODAL
    ============================================================
    */

    function actualizarTotalModal() {


        const cantidad =
            parseFloat(
                document.getElementById(
                    "cantidadProducto"
                ).value
            ) || 0;


        const precio =
            parseFloat(
                document.getElementById(
                    "precioProducto"
                ).value
            ) || 0;


        const descuento =
            parseFloat(
                document.getElementById(
                    "descuentoProducto"
                ).value
            ) || 0;


        const subtotal =
            cantidad * precio;


        const montoDescuento =
            subtotal * descuento / 100;


        const total =
            subtotal - montoDescuento;


        document.getElementById(
            "totalProductoModal"
        ).innerText =
            "Gs. " + formatearNumero(total);

    }



    /*
    ============================================================
    EVENTOS DEL MODAL
    ============================================================
    */

    document.getElementById(
        "cantidadProducto"
    ).addEventListener(
        "input",
        actualizarTotalModal
    );


    document.getElementById(
        "descuentoProducto"
    ).addEventListener(
        "input",
        actualizarTotalModal
    );



    /*
    ============================================================
    AGREGAR DETALLE
    ============================================================
    */

    function agregarDetalle() {


        const producto =
            document.getElementById(
                "productoSeleccionado"
            );


        const productoId =
            producto.value;


        const productoTexto =
            producto.options[
                producto.selectedIndex
            ].text;


        const codigo =
            document.getElementById(
                "codigoProducto"
            ).value;


        const cantidad =
            parseFloat(
                document.getElementById(
                    "cantidadProducto"
                ).value
            ) || 0;


        const precio =
            parseFloat(
                document.getElementById(
                    "precioProducto"
                ).value
            ) || 0;


        const descuentoPorcentaje =
            parseFloat(
                document.getElementById(
                    "descuentoProducto"
                ).value
            ) || 0;



        /*
        VALIDACIONES
        */

        if (!productoId) {

            alert(
                "Seleccione un producto."
            );

            return;

        }


        const productoYaExiste = detalles.some(
            detalle => String(detalle.productoId) === String(productoId)
        );

        if (productoYaExiste) {
             Swal.fire({
                    icon: 'warning',
                    title: 'Producto ya seleccionado',
                    text: 'Este producto ya fue agregado al detalle.',
                    confirmButtonText: 'Entendido',
                    confirmButtonColor: '#0d6efd',
                    background: '#ffffff',
                    borderRadius: '15px'
                });
            return;
        }


        if (cantidad <= 0) {

            alert(
                "La cantidad debe ser mayor a 0."
            );

            return;

        }


        if (precio <= 0) {

            alert(
                "El producto no tiene un precio válido."
            );

            return;

        }



        /*
        CÁLCULOS
        */

        const subtotal =
            cantidad * precio;


        const descuento =
            subtotal *
            descuentoPorcentaje /
            100;


        const total =
            subtotal -
            descuento;



        detalles.push({

            codigo: codigo,

            productoId: productoId,

            producto: productoTexto,

            cantidad: cantidad,

            precio: precio,

            descuentoPorcentaje:
                descuentoPorcentaje,

            descuento: descuento,

            subtotal: subtotal,

            total: total

        });


        renderizarDetalles();



        const modal =
            bootstrap.Modal.getInstance(
                document.getElementById(
                    "modalProducto"
                )
            );


        modal.hide();



        /*
        LIMPIAR
        */

        limpiarModal();

    }



    /*
    ============================================================
    MOSTRAR DETALLES
    ============================================================
    */
function renderizarDetalles() {

    const tbody = document.getElementById("detalleBody");

    const detallesHidden = document.getElementById("detallesHidden");

    tbody.innerHTML = "";

    detallesHidden.innerHTML = "";


    if (detalles.length === 0) {

        tbody.innerHTML = `
            <tr>

                <td colspan="8"
                    class="text-center text-muted py-4">

                    <i class="bi bi-cart-x fs-3"></i>

                    <br>

                    No se encontraron registros

                </td>

            </tr>
        `;

        actualizarTotales();

        return;
    }


    detalles.forEach((detalle, index) => {

        /*
        =====================================================
        FILA VISIBLE
        =====================================================
        */

        const fila = document.createElement("tr");

        fila.innerHTML = `

            <td>
                ${detalle.codigo || "-"}
            </td>

            <td>
                ${detalle.producto}
            </td>

            <td class="text-center">
                ${detalle.cantidad}
            </td>

            <td class="text-end">
                ${formatearNumero(detalle.precio)}
            </td>

            <td class="text-center">
                ${detalle.descuentoPorcentaje}%
            </td>

            <td class="text-end">
                ${formatearNumero(detalle.descuento)}
            </td>

            <td class="text-end fw-bold">
                ${formatearNumero(detalle.total)}
            </td>

            <td class="text-center">

                <button type="button"
                        class="btn btn-danger btn-sm"
                        onclick="eliminarDetalle(${index})">

                    <i class="bi bi-trash"></i>

                </button>

            </td>
        `;

        tbody.appendChild(fila);


        /*
        =====================================================
        DATOS OCULTOS PARA SPRING BOOT
        =====================================================
        */

        detallesHidden.innerHTML += `

            <input type="hidden"
                   name="detalles[${index}].productoId"
                   value="${detalle.productoId}">

            <input type="hidden"
                   name="detalles[${index}].cantidad"
                   value="${detalle.cantidad}">

            <input type="hidden"
                   name="detalles[${index}].precio_unitario"
                   value="${detalle.precio}">

            <input type="hidden"
                   name="detalles[${index}].subtotal"
                   value="${detalle.subtotal}">



        `;

    });


    actualizarTotales();
}


    /*
    ============================================================
    ELIMINAR DETALLE
    ============================================================
    */

    function eliminarDetalle(index) {


        detalles.splice(index, 1);


        renderizarDetalles();

    }



    /*
    ============================================================
    TOTALES
    ============================================================
    */

    function actualizarTotales() {


        let subtotal = 0;

        let descuento = 0;

        let total = 0;



        detalles.forEach(
            detalle => {

                subtotal +=
                    detalle.subtotal;

                descuento +=
                    detalle.descuento;

                total +=
                    detalle.total;

            }
        );



        document.getElementById(
            "subtotalFactura"
        ).innerText =
            "Gs. " +
            formatearNumero(subtotal);



        document.getElementById(
            "descuentoFactura"
        ).innerText =
            "Gs. " +
            formatearNumero(descuento);



        document.getElementById(
            "totalFactura"
        ).innerText =
            "Gs. " +
            formatearNumero(total);

        document.getElementById("totalFacturaHidden").value = total;

    }



    /*
    ============================================================
    LIMPIAR MODAL
    ============================================================
    */

    function limpiarModal() {


        document.getElementById(
            "codigoProducto"
        ).value = "";


        document.getElementById(
            "productoSeleccionado"
        ).value = "";


        document.getElementById(
            "cantidadProducto"
        ).value = 1;


        document.getElementById(
            "precioProducto"
        ).value = 0;


        document.getElementById(
            "descuentoProducto"
        ).value = 0;


        document.getElementById(
            "totalProductoModal"
        ).innerText = "Gs. 0";

    }



    /*
    ============================================================
    FORMATO DE NÚMEROS
    ============================================================
    */

    function formatearNumero(valor) {


        return Number(valor).toLocaleString(
            "es-PY",
            {
                minimumFractionDigits: 0,
                maximumFractionDigits: 0
            }
        );

    }



    /*
     * =========================================================
     * CONVERTIR Gs. 49.000 A 49000
     * =========================================================
     */

    function parsearGuaranies(valor) {


        if (!valor) {

            return 0;

        }


        return parseFloat(

            valor
                .replace('Gs.', '')
                .replace('Gs', '')
                .replace(/\./g, '')
                .replace(',', '.')
                .trim()

        ) || 0;

    }


    /*
     * =========================================================
     * FORMATEAR GUARANÍES
     * =========================================================
     */

    function formatearGuaranies(valor) {


        return 'Gs. ' +
            Math.round(valor)
                .toLocaleString('es-PY');

    }

