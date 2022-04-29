import React, { useCallback, useEffect } from "react";
import _ from "lodash";

const Infinity = (props) => {
  const { children, callNext, paging, is_loading } = props;

  const _handleScroll = _.throttle(() => {
    const { innerHeight } = window;
    const { scrollHeight } = document.body;
    const scrollTop =
      (document.documentElement && document.documentElement.scrollTop) ||
      document.body.scrollTop;

    if (scrollHeight - innerHeight - scrollTop < 500 && paging.next < 5) {
      callNext();
    }
  }, 300);

  const handleScroll = useCallback(_handleScroll, []);

  useEffect(() => {
    if (is_loading) {
      return;
    }
    if (paging.next < 5) {
      window.addEventListener("scroll", handleScroll);
    }
  }, [paging]);
  return <div>{children}</div>;
};
Infinity.defaultProps = {
  children: null,
  callNext: () => {},
  is_loading: false,
};

export default Infinity;
