package it.chicio.productmvp.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import it.chicio.productmvp.R;
import it.chicio.productmvp.model.Product;
import it.chicio.productmvp.presenter.ProductsPresenter;

class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {
    private Product[] products;
    private ProductsPresenter productsPresenter;

    ProductsAdapter(Product[] products, ProductsPresenter productsPresenter) {
        this.products = products;
        this.productsPresenter = productsPresenter;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowProductView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_product, parent, false);
        return new ViewHolder(rowProductView, productsPresenter);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(products[position].getName());
        holder.product = products[position];
    }

    @Override
    public int getItemCount() {
        return products.length;
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ProductsPresenter productsPresenter;
        TextView textView;
        Product product;

        ViewHolder(View itemView, ProductsPresenter productsPresenter) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.product_name_text_view);
            textView.setOnClickListener(this);
            this.productsPresenter = productsPresenter;
        }

        @Override
        public void onClick(View v) {
            productsPresenter.onSelected(product);
        }
    }
}
