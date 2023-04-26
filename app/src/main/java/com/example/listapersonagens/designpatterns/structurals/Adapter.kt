package com.example.listapersonagens.designpatterns.structurals

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 *
 * O adapter permite que objetos e interfaces incompatíveis colaborarem entre sí.
 *O objetivo é adaptar interfaces e objetos através de uma classe ou uma interface padrão.
 *
 * Vantagens
 * - Manutenção de código pelo principio aberto e fechado
 * - Separação de responsabilidades que em casos comuns seria da View
 * */

/**
 * No caso do android, um dos exemplos de adapter é a utilização do adapter do recycler view, além
 * do Array Adapter, ListAdapter, SimpleAdapter, PagerAdapter, entre outros.
 * Utilizamos ele para linkar o código do model com o código xml das views.
 *
 * Desta forma, podemos indicar qual a forma que desejamos criar e mostrar.
 * */
class AdapterItem : RecyclerView.Adapter<AdapterItem.ItemViewHolder>() {
    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(parent)
    }

    override fun getItemCount(): Int = 0

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
    }
}
