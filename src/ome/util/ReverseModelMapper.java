/*
 * ome.util.ReverseModelMapper
 *
 *------------------------------------------------------------------------------
 *
 *  Copyright (C) 2005 Open Microscopy Environment
 *      Massachusetts Institute of Technology,
 *      National Institutes of Health,
 *      University of Dundee
 *
 *
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation; either
 *    version 2.1 of the License, or (at your option) any later version.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 *
 *    You should have received a copy of the GNU Lesser General Public
 *    License along with this library; if not, write to the Free Software
 *    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 *------------------------------------------------------------------------------
 */

package ome.util;

//Java imports
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

//Third-party libraries
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

//Application-internal dependencies
import ome.api.ModelBased;
import ome.model.IObject;
import ome.model.meta.Event;


/** 
 * @author  Josh Moore &nbsp;&nbsp;&nbsp;&nbsp;
 * 				<a href="mailto:josh.moore@gmx.de">josh.moore@gmx.de</a>
 * @version 1.0 
 * <small>
 * (<b>Internal version:</b> $Rev$ $Date$)
 * </small>
 * @since 1.0
 */
public class ReverseModelMapper { // extends ContextFilter {
	
	protected static Log log = LogFactory.getLog(ModelMapper.class);
	
//	/** TODO
//	 * identity versus null mappins
//	 * @return
//	 */
//	protected abstract Map c2c();

	protected Map target2model = new IdentityHashMap();//FIXME not thread safe. 
	
    public IObject map(ModelBased source){
        if (target2model.containsKey(source)){
            return (IObject) target2model.get(source);
        } else {
            IObject model = source.asIObject(this);
            target2model.put(source,model);
            return model;    
        }
	}
	
//	public Filterable filter(String fieldId, Filterable source) {
//		Filterable o = super.filter(fieldId,source);
//		ModelBased target = (ModelBased) findTarget(o);
//		fillTarget(source,target);
//		return o;
//	}
//
//	public Collection filter(String fieldId, Collection source) {
//		Collection o = super.filter(fieldId,source);
//		Collection target = findCollection(o);
//		fillCollection(source,target);	
//		return o;
//	}
//	
//	public Map filter(String fieldId, Map source) {
//		Map o = super.filter(fieldId,source);
//		Map target = findMap(o);
//		fillMap(source,target);	
//		return o;
//	}
//	
//	public Object findTarget(Object current){
//		// IMMUTABLES
//		if (null == current |
//				current instanceof Integer | 
//				current instanceof String) // TODO can use findTarget then for these as well.
//		{
//			return current;
//		} else 
//		// Special cases TODO put into doFindTarget
//		if (current instanceof Date){
//			return new Timestamp(((Date)current).getTime()); // FIXME should cache these in model2pojo
//		}
//		
//		Object target = model2target.get(current);
//		if (null == target) {
//			Class targetType = (Class) c2c().get(current.getClass());
//			if (null != targetType){
//				try {
//					target = targetType.newInstance();
//				} catch (Exception e) {
//					throw new RuntimeException("Internal error: could not instantiate object of type "+targetType+" while trying to map "+current,e);
//				}
//				model2target.put(current,target);
//			}
//		}
//		return target;
//	}
//
//	
//	public Collection findCollection(Collection source){
//		if (source==null) return null;
//		
//		Collection target = (Collection) model2target.get(source);
//		if (null==target) {
//			try { 
//				target = (Collection) source.getClass().newInstance(); // TODO do we want to use the map here?
//				model2target.put(source,target);
//			} catch (InstantiationException ie){
//				throw new RuntimeException(ie);
//			} catch (IllegalAccessException iae){
//				throw new RuntimeException(iae);
//			}
//		}
//		return target;
//	}
//	
//	public Map findMap(Map source){
//		if (source==null) return null;
//
//		Map target = (Map) model2target.get(source);
//		if (null==target){
//			try { 
//				target = (Map) source.getClass().newInstance();
//				model2target.put(source,target);
//			} catch (InstantiationException ie){
//				throw new RuntimeException(ie);
//			} catch (IllegalAccessException iae){
//				throw new RuntimeException(iae);
//			}
//		}
//		return target;
//	}
//
//	private void fillTarget(Filterable source, ModelBased target){
//		if (source!=null && target != null){
//			// FIXME XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXtarget.copy(((IObject)source),this);		
//		}
//	}
//	
//	private void fillCollection(Collection source, Collection target){
//		if (source!=null && target != null){
//			for (Iterator it = source.iterator(); it.hasNext();) {
//				Object o = it.next();
//				target.add(this.findTarget(o));
//			}
//		}
//	}
//	
//	private void fillMap(Map source, Map target){
//		if (source!=null && target != null){
//			for (Iterator it = source.keySet().iterator(); it.hasNext();) {
//				Object o = it.next();
//				target.put(findTarget(o),findTarget(source.get(o)));
//			}
//		}
//	}
//	
//	public Timestamp event2timestamp(Event event){
//		if (event==null) return null;
//        if (event.getTime()==null) return null;
//		return event.getTime();
//	}
//    
//	public int nullSafeInt(Integer i){
//		if (i==null) return 0;
//		return i.intValue();
//	}
//	
//	public long nullSafeLong(Long l){
//		if (l==null) return 0;
//		return l.longValue();
//	}
//    
//    public double nullSafeDouble(Double d){
//        if (d==null) return 0.0;
//        return d.doubleValue();
//    }
//    
//    public float nullSafeFloat(Float f){
//        if (f==null) return 0.0F;
//        return f.floatValue();
//    }

}