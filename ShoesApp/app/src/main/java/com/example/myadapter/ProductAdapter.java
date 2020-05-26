package com.example.myadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.model.Products;
import com.example.shoesapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter {
    ArrayList<Products> arrayProducts;
    Context context;
    public  ProductAdapter(ArrayList<Products> arr, Context context){
        this.arrayProducts = arr;
        this.context = context;
    }
    //đổ hết dữ liệu trong mảng ra
    @Override
    public int getCount() {
        return arrayProducts.size();
    }
    //Laasytuwfng thuộc tính trong bảng
    @Override
    public Object getItem(int position) {
        return arrayProducts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class ViewHolder {
        TextView name;
        ImageView img;
    }

        @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null) // view rỗng
        {
            viewHolder = new ViewHolder();
            //get service là layout ra
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            //gán view vào
            convertView = inflater.inflate(R.layout.row_product,null);
            // gán id cho các thuộc tính layout
            viewHolder.img = (ImageView) convertView.findViewById(R.id.imgProduct);
            viewHolder.name = (TextView) convertView.findViewById(R.id.nameProduct);
            //gán ID vào trong viewHolder
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();// lấy lại tag đã gán vào

        }
        //lấy dữ liệu ra từ mảng
        Products product = (Products) getItem(position);
        viewHolder.name.setText(product.getProductName());
        //đọc đường dẫn url từ internet
        Picasso.with(context).load(product.getImgProduct())// hình bị lỗi k load dc
                .into(viewHolder.img); // nếu thành cồn thì gán ảnh vào

        return convertView;
    }
}
