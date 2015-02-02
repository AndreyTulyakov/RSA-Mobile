package mhyhre.rsamobile;

import java.util.Locale;

import mhyhre.rsamobile.fragments.OperationsFragment;
import mhyhre.rsamobile.fragments.KeyFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

public class MainActivity extends FragmentActivity {

	
	private SectionsPagerAdapter mSectionsPagerAdapter;
	private ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
	}

	
	public class SectionsPagerAdapter extends FragmentPagerAdapter {
		
		private static final int COUNT_OF_PAGES = 2;

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {

			Fragment result = null;
			
			switch(position) {
			case 0:
				result = KeyFragment.newInstance(position + 1);
				break;
			case 1:
				result = OperationsFragment.newInstance(position + 1);
				break;
			}
			
			return result;
		}

		@Override
		public int getCount() {
			return COUNT_OF_PAGES;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			}
			return null;
		}
	}


	

}
