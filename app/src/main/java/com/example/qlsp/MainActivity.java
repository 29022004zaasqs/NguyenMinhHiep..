package com.example.qlsp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.model.DanhMuc;
import com.example.model.SanPham;

public class MainActivity extends AppCompatActivity {
    Spinner spnDanhMuc;
    ArrayAdapter<DanhMuc> danhMucAdapter;
    ArrayAdapter<SanPham> sanPhamAdapter;
    EditText edtMaSP, edtTenSP, edtGia;
    ListView lvSanPham;
    Button btnThem;
    DanhMuc selectedDanhMuc=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addView();
        addEvent();
    }



    private void addView() {
        spnDanhMuc=findViewById(R.id.spnSanPham);
        danhMucAdapter=new ArrayAdapter<DanhMuc>(MainActivity.this, android.R.layout.simple_spinner_item);
        spnDanhMuc.setAdapter(danhMucAdapter);

        lvSanPham=findViewById(R.id.lvSanPham);
        sanPhamAdapter=new ArrayAdapter<SanPham>(MainActivity.this, android.R.layout.simple_list_item_1);
        lvSanPham.setAdapter(sanPhamAdapter);

        edtMaSP=findViewById(R.id.edtMaSP);
        edtGia=findViewById(R.id.edtGiaSP);
        edtTenSP=findViewById(R.id.edtTenSP);
        btnThem=findViewById(R.id.btnThem);
        danhMucAdapter.add(new DanhMuc("1","Rươu"));
        danhMucAdapter.add(new DanhMuc("2","Bia"));
        danhMucAdapter.add(new DanhMuc("3","Nước ngọt"));
        danhMucAdapter.add(new DanhMuc("4","Thuốc lá"));
    }
    private void addEvent() {
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xulyNhapSanPham();
            }
        });
        spnDanhMuc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                selectedDanhMuc=danhMucAdapter.getItem(i);
                sanPhamAdapter.clear();
                sanPhamAdapter.addAll(selectedDanhMuc.getSanPhams());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void xulyNhapSanPham(){
        SanPham sanPham=new SanPham(edtMaSP.getText().toString(),edtTenSP.getText().toString(),Integer.parseInt(edtGia.getText().toString()));
        selectedDanhMuc.getSanPhams().add(sanPham);
        sanPhamAdapter.clear();
        sanPhamAdapter.addAll(selectedDanhMuc.getSanPhams());
    }
}